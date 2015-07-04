<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<select id="cron_expression_select" class="form-control">
  <option value="0 0 0 * * ?">凌晨0点执行</option>
  <option value="0 0 1 * * ?">凌晨1点执行</option>
  <option value="0 0 2 * * ?">凌晨2点执行</option>
  <option value="0 0 3 * * ?">凌晨3点执行</option>
  <option value="0 0 4 * * ?">凌晨4点执行</option>
  <option value="0 0 5 * * ?">早上5点执行</option>
  <option value="0 0 6 * * ?">早上6点执行</option>
  <option value="0 0 7 * * ?">早上7点执行</option>
  <option value="0 0 8 * * ?">上午8点执行</option>
  <option value="0 0 9 * * ?">上午9点执行</option>
  <option value="0 0 10 * * ?">上午10点执行</option>
  <option value="0 0 11 * * ?">上午11点执行</option>
  <option value="0 0 12 * * ?">中午12点执行</option>
  <option value="0 0 13 * * ?">下午13点执行</option>
  <option value="0 0 14 * * ?">中午14点执行</option>
  <option value="0 0 15 * * ?">中午15点执行</option>
  <option value="0 0 16 * * ?">中午16点执行</option>
  <option value="0 0 17 * * ?">傍晚17点执行</option>
  <option value="0 0 18 * * ?">傍晚18点执行</option>
  <option value="0 0 19 * * ?">傍晚19点执行</option>
  <option value="0 0 20 * * ?">晚上20点执行</option>
  <option value="0 0 21 * * ?">晚上21点执行</option>
  <option value="0 0 22 * * ?">夜晚22点执行</option>
  <option value="0 0 23 * * ?">夜晚23点执行</option>
  <option value="0 0 1 ? * MON">每周一凌晨1点执行</option>
  <option value="0 0 1 ? * TUE">每周二凌晨1点执行</option>
  <option value="0 0 1 ? * WED">每周三凌晨1点执行</option>
  <option value="0 0 1 ? * THU">每周四凌晨1点执行</option>
  <option value="0 0 1 ? * FRI">每周五凌晨1点执行</option>
  <option value="0 0 1 ? * SAT">每周六凌晨1点执行</option>
  <option value="0 0 1 ? * SUN">每周日凌晨1点执行</option>
</select>