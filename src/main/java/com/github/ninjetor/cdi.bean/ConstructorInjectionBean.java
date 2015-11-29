package com.github.ninjetor.cdi.bean;

import javax.inject.Inject;

/**
 * Created by pestano on 29/11/15.
 */
public class ConstructorInjectionBean {

    MyBean myBean;

    @Inject
    public ConstructorInjectionBean(MyBean myBean){
        this.myBean = myBean;
    }
}
