package com.eep.EEP_Consulting.Component;

import com.eep.EEP_Consulting.Model.Camionero;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("TrazasComponent")
public class TrazasComponent {
    private static final Log LOG = LogFactory.getLog(TrazasComponent.class);

    public void test(List<Camionero> test){
        for (int i = 0; i < test.size(); i++){
            System.out.println(test);
        }
    }
}
