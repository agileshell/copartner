package test;

import org.junit.Test;

import com.insoul.copartner.domain.Admin;
import com.insoul.copartner.utils.Permission;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月22日 下午10:54:10
 */
public class AdminTest {

    @Test
    public void test() {
        Admin admin = new Admin();
        admin
//        .addPermission(Permission.SuperAdmin)
//        .addPermission(Permission.Admin)
        .addPermission(Permission.User)
        ;
        System.out.println(admin.getPermission());
        System.out.println("SuperAdmin : " + admin.hasPermission(Permission.SuperAdmin));
        System.out.println("Admin : " + admin.hasPermission(Permission.Admin));
        System.out.println("User : " + admin.hasPermission(Permission.User));
    }
}
