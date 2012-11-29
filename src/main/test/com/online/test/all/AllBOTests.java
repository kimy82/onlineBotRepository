package com.online.test.all;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.online.test.bo.TestPlatsBO;
import com.online.test.bo.TestRestaurantsBO;
import com.online.test.bo.TestUserBO;

@RunWith(Suite.class)
@SuiteClasses({TestPlatsBO.class,  TestRestaurantsBO.class,TestUserBO.class })
public class AllBOTests{

}
