package com.livejournal.uitests.utility;

import com.livejournal.uisteps.thucydides.WebTest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s.savinykh
 */
public class GetUsers extends WebTest {

    private String scriptWithMobileView() {  //формирование запроса на включенную опцию Mobile View
        return "SELECT user.user "
                + "FROM user "
                + "left join lj_c2.userproplite2 on user.userid = lj_c2.userproplite2.userid "
                + "left join lj_c1.userproplite2 on user.userid = lj_c1.userproplite2.userid "
                + "WHERE lj_c2.userproplite2.upropid = 402 "
                + "OR lj_c1.userproplite2.upropid = 402;";

    }

    private String[] scriptAllUsers(String needPass, String userType, Boolean paid, String style) { //формирование кластерных запросов для поиска подходящих пользователей
        String[] script = new String[3];
        script[0] = "";
        for (Integer i = 1; i < 3; i++) {
            script[i] = "SELECT DISTINCT user.user "
                    + "FROM user ";
            if (needPass.toUpperCase().equals("NEED PASS")) {
                script[i] += "LEFT JOIN password ON user.userid=password.userid ";
            }
            script[i] += "left join lj_c" + i.toString() + ".userproplite2 on user.userid = lj_c" + i.toString() + ".userproplite2.userid "
                    + "left join s2styles on lj_c" + i.toString() + ".userproplite2.value = s2styles.styleid "
                    + "left join lj_c" + i.toString() + ".log2 on lj_c" + i.toString() + ".log2.journalid = user.userid "
                    + "WHERE  lj_c" + i.toString() + ".userproplite2.upropid = 96 "
                    + "AND user.statusvis = 'V' ";
            if (needPass.toUpperCase().equals("NEED PASS")) {
                script[i] += "AND password.password not like '%md5%' ";
            }
            script[i] += "AND lj_c" + i.toString() + ".log2.security = 'public' ";
            switch (userType.toUpperCase()) {
                case "JOURNAL":
                    script[i] += "AND user.journaltype = 'P' ";
                    break;
                case "COMMUNITY":
                    script[i] += "AND user.journaltype = 'C' ";
                    break;
                default:
                    script[i] += "AND user.journaltype = 'P' ";
                    break;
            }

            if (paid) {
                script[i] += "AND ((user.caps & 1<<3 = 8) =1 or (user.caps & 1<<4=16)=1) ";
            } else {
                script[i] += "AND ((user.caps & 1<<3 = 8) =0 and (user.caps & 1<<4=16)=0) ";
            }

            switch (style.toUpperCase()) {
                case "AIR":
                    script[i] += "AND s2styles.name like '%wizard-air/default_theme%';";
                    break;
                case "CHAMELEON":
                    script[i] += "AND s2styles.name like '%chameleon%' "
                            + "AND s2styles.name !='wizard-chameleon/__none' "
                            + "AND s2styles.name NOT LIKE '%chameleonljart%' "
                            + "AND s2styles.name NOT LIKE '%chamljartv2%' "
                            + "AND s2styles.name !='wizard-chameleon/__headerin_alpha' "
                            + "AND s2styles.name !='wizard-chameleon/bright-decorations' "
                            + "AND s2styles.name !='wizard-chameleon/orange-tinsel';";
                    break;
                case "NONADAPTIVE":
                    script[i] += "AND s2styles.name not like '%wizard-air/default_theme%' and  s2styles.name not like '%chameleon%';";
                    break;
                default:
                    script[i] += "AND s2styles.name not like '%wizard-air/default_theme%' and  s2styles.name not like '%chameleon%';";
                    break;
            }
        }
        return script;
    }

    private List<ArrayList<String>> getAllUsers(String needPass, String userType, Boolean paid, String style) {
        return workWithDB().conect()
                .select(scriptWithMobileView(), "user")
                .select(scriptAllUsers(needPass, userType, paid, style)[1], "user")
                .select(scriptAllUsers(needPass, userType, paid, style)[2], "user")
                .finish();
    }

    public String getNeededUser(String needPass, String userType, Boolean paid, Boolean mobileView, String style) {
        int index = 0;
        ArrayList<String> neededUsers = new ArrayList<>();
        List<ArrayList<String>> users = getAllUsers(needPass, userType, paid, style);
        users.get(1).addAll(users.get(2)); //соединение результатов с двух кластеров в один список
        users.get(1).remove("system"); //удаление пользователя system
        if (mobileView) {
            for (int i = 0; i < users.get(0).size(); i++) {
                if (users.get(1).contains(users.get(0).get(i))) {
                    neededUsers.add(users.get(0).get(i));
                }
            }
        } else {
            for (int i = 0; i < users.get(0).size(); i++) {
                users.get(1).remove(users.get(0).get(i));
            }
            neededUsers.addAll(users.get(1));
        }
        if (!neededUsers.isEmpty()) {
            index = (int) (Math.random() * (neededUsers.size()));
        } else {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! НЕТ ДАННЫХ !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            neededUsers.set(index, "");
        }
        return neededUsers.get(index);
    }
}
