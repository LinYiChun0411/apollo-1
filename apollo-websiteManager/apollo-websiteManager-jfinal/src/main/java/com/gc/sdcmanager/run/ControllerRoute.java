package com.gc.sdcmanager.run;

import com.gc.sdcmanager.web.controller.HgaccountController;
import com.gc.sdcmanager.web.controller.HgurlController;
import com.gc.sdcmanager.web.controller.HostController;
import com.gc.sdcmanager.web.controller.IndexController;
import com.gc.sdcmanager.web.controller.SendResultController;
import com.gc.sdcmanager.web.controller.SportsEventsController;
import com.gc.sdcmanager.web.controller.UserController;
import com.jfinal.config.Routes;

public class ControllerRoute extends Routes {
	public void config() {
		add("/", IndexController.class, "sys");
		add("/user", UserController.class, "sys/user");
		add("/host", HostController.class, "sys/host");
		add("/sendResult", SendResultController.class, "sys/sendResult");
		add("/sportsevents", SportsEventsController.class, "sys/sportsevents");
		add("/hgaccount", HgaccountController.class, "sys/hgaccount");
		add("/hgurl", HgurlController.class, "sys/hgurl");
	}
}