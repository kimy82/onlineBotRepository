package com.online.test.all;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.online.test.actions.TestLoginAction;
import com.online.test.actions.admin.TestMantenimentPlatAction;
import com.online.test.actions.admin.TestMantenimentRestaurantsAction;
 
@RunWith(Suite.class)
@SuiteClasses({TestMantenimentPlatAction.class,  TestMantenimentRestaurantsAction.class,TestLoginAction.class })
public class AllActionTests{

}
