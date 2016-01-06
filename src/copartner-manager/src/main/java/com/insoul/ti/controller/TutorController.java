package com.insoul.ti.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.constant.GlobalProperties;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.constant.UserStatus;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.ILocationDao;
import com.insoul.copartner.dao.IStartupRoleDao;
import com.insoul.copartner.dao.IStartupStatusDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.TutorCriteria;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.Location;
import com.insoul.copartner.domain.StartupRole;
import com.insoul.copartner.domain.StartupStatus;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.copartner.util.PasswordUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.TutorAddRequest;
import com.insoul.ti.req.TutorListRequest;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.shiro.Permission;
import com.insoul.ti.vo.TutorListVO;

@Controller
@RequestMapping("/tutor")
@Permission("authc")
public class TutorController extends WebBase {

	@Resource
	private IUserDao userDao;

	@Resource
	private IIndustryDomainDao industryDomainDao;

	@Resource
	private ILocationDao locationDao;

	@Resource
	private IStartupRoleDao startupRoleDao;

	@Resource
	private IStartupStatusDao startupStatusDao;

	@RequestMapping("/list")
	public ModelAndView list(TutorListRequest requestData) {
		ModelAndView mv = createModelView("tutor_list", requestData);
		PageQuery query = requestData.init().getQuery();

		List<TutorListVO> tutorVOs = new ArrayList<TutorListVO>();

		TutorCriteria criteria = new TutorCriteria();
		criteria.setStatus(new String[] { "active" });
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());

		List<User> users = userDao.queryTutor(criteria);
		for (User user : users) {
			TutorListVO tutorVO = new TutorListVO();
			tutorVO.setTutorId(user.getId());
			tutorVO.setName(user.getName());
			tutorVO.setTitle(user.getTitle());
			tutorVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));
			tutorVO.setManagementExp(user.getManagementExp());
			tutorVO.setStartupExp(user.getStartupExp());

			Long professionId = user.getProfessionId();
			if (professionId == 1) {
				tutorVO.setProfessionName("学术型");
			} else if (professionId == 2) {
				tutorVO.setProfessionName("实业型");
			}

			if (StringUtils.isNotBlank(user.getDomains())) {
				String domainIds[] = user.getDomains().split(",");
				IndustryDomain industryDomain = industryDomainDao.get(Long.valueOf(domainIds[0]));
				if (null != industryDomain) {
					tutorVO.setDomain(industryDomain.getName());
				}
			}

			tutorVOs.add(tutorVO);
		}

		Long count = userDao.countTutor(criteria);
		query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
		mv.addObject("query", query);
		mv.addObject("tutors", tutorVOs);
		mv.addObject("success", CollectionUtils.isNotEmpty(tutorVOs));
		mv.addObject("req", request);
		mv.addObject("viewname", "tutor_list");
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		ModelAndView mv = createModelView("tutor_add", req);
		mv.addObject("viewname", "tutor_list");
		return mv;
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(TutorAddRequest request) throws CException {
		String mobile = request.getMobile();
		if (null != userDao.getUserByMobile(mobile)) {
			throw CExceptionFactory.getException(CException.class, ResponseCode.MOBILE_REGISTERED);
		}

		Date now = new Date();

		MultipartFile image = request.getAvatar();
		String path = StringUtils.EMPTY;
		if (image != null) {
			String fileType = FileUtil.getFileType(image.getOriginalFilename());
			if (StringUtils.isNotBlank(fileType)) {
				String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
				try {
					path = CDNUtil.uploadFile(image.getInputStream(), fileName);
				} catch (Exception e) {
					log.error("UploadFile Error.", e);
				}
			}
		}

		User tutor = new User();
		tutor.setName(request.getName());
		tutor.setMobile(mobile);
		tutor.setAvatar(path);
		tutor.setIntroduction(request.getIntroduction());
		tutor.setTitle(request.getTitle());
		tutor.setStartupExp(request.getStartupExp());
		tutor.setManagementExp(request.getManagementExp());
		tutor.setCreated(now);

		String salt = PasswordUtil.genSalt();
		tutor.setSalt(salt);
		String password = mobile.substring(mobile.length() - 6, mobile.length());
		tutor.setPassword(PasswordUtil.encodePassword(password, salt));

		setDefaultValue(tutor);

		userDao.save(tutor);

		return new ModelAndView("redirect:/tutor/list");
	}

	private void setDefaultValue(User user) {
		user.setStatus(UserStatus.ACTIVE.getValue());

		List<StartupRole> roles = startupRoleDao.findAll();
		if (null != roles && roles.size() > 0) {
			user.setStartupRoleId(roles.get(0).getId());
		}

		List<StartupStatus> statuses = startupStatusDao.findAll();
		if (null != statuses && statuses.size() > 0) {
			user.setStartupStatusId(statuses.get(0).getId());
		}

		Long locationId = GlobalProperties.DEFAULT_LOCATION_ID.longValue();
		if (0 != locationId) {
			Location location = locationDao.get(locationId);
			if (null != location) {
				user.setLocationId(locationId);

				// 缓存地区全名
				StringBuilder fullLocation = new StringBuilder();
				fullLocation.append(location.getName());

				Location parentLocation = locationDao.get(location.getParentId());
				if (null != parentLocation) {
					fullLocation.append("|").append(parentLocation.getName());
				}
				user.setFullLocation(fullLocation.toString());
			}
		}

	}
}