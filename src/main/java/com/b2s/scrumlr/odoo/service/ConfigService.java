package com.b2s.scrumlr.odoo.service;

import com.b2s.scrumlr.admin.dao.UserDaoImpl;
import com.b2s.scrumlr.odoo.model.TimesheetTask;
import com.b2s.scrumlr.odoo.model.User;
import com.b2s.scrumlr.odoo.utils.JsonUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ConfigService {

    @Autowired
    private UserDaoImpl userDao;

    public List<User> getUsers(){
        final List<User> users = userDao.getUsers();
        final String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        for(final User user : users){
            if(Objects.isNull(user.getDate())){
                user.setDate(date);
            }
            for(final TimesheetTask task : user.getTimesheetTasks()){
                task.setCurrentDate(user.getDate());
            }
        }

        return users;
//        final User user = new User();
//        final OdooAccount account = new OdooAccount();
//        account.setLogin("sliu");
//        account.setPassword("Dashabi123");
//        user.setAccount(account);
//        user.setDate("2017-03-20");
//        user.setMailAddress("gxi@bridge2solutions.com");
//        user.setName("Gavin");
//        final TimesheetTask timesheetTask = new TimesheetTask();
//        timesheetTask.setProject("Projects / Aspire - Phoenix - Activity");
//        timesheetTask.setTask("Testing");
//        timesheetTask.setHours(4);
//        timesheetTask.setCurrentDate(user.getDate());
//
//        final TimesheetTask timesheetTask1 = new TimesheetTask();
//        timesheetTask1.setProject("Projects / Aspire - Prod Support");
//        timesheetTask1.setTask("Testing");
//        timesheetTask1.setHours(4);
//        timesheetTask1.setCurrentDate(user.getDate());
//
//
//        user.setTimesheetTasks(Arrays.asList(timesheetTask, timesheetTask1));
//        return Collections.singletonList(user);
    }

}