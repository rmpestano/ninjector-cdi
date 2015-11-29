package com.github.ninjector.cdi;

import com.github.ninjetor.cdi.bean.ConstructorInjectionBean;
import com.github.ninjetor.cdi.bean.FieldInjectionBean;
import com.github.ninjetor.cdi.bean.MyBean;
import com.github.ninjetor.cdi.extension.NinjectorExtension;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by pestano on 29/11/15.
 */

@RunWith(JUnit4.class)
public class NinjectorIt {

    WeldContainer weldContainer;

    @After
    public void stopContainer(){
        if(weldContainer != null){
            weldContainer.shutdown();
        }
    }

    @Test
    public void shouldBootCdiContainerUsingFieldInjection(){
        Weld weld = new Weld();
        weld.disableDiscovery();
        weld.addExtension(new NinjectorExtension());
        weldContainer = weld.beanClasses(FieldInjectionBean.class, MyBean.class).initialize();
        FieldInjectionBean fieldInjectionBean = weldContainer.select(FieldInjectionBean.class).get();
        assertNotNull(fieldInjectionBean);
    }

    @Test(expected = org.jboss.weld.exceptions.DefinitionException.class)
    public void shouldNotBootCdiContainerUsingConstrctorInjection(){
        Weld weld = new Weld();
        weld.disableDiscovery();
        weld.addExtension(new NinjectorExtension());
        weldContainer = weld.beanClasses(ConstructorInjectionBean.class, MyBean.class).initialize();
        fail("should not reach here");
    }
}
