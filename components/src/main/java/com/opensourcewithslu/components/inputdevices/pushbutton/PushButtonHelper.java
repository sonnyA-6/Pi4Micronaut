package com.opensourcewithslu.components.inputdevices.pushbutton;

import com.opensourcewithslu.components.inputdevices.InputDevice;
import com.opensourcewithslu.components.outputdevices.led.LEDHelper;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalStateChangeListener;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.Callable;

@Singleton
public class PushButtonHelper extends InputDevice {
    private static final Logger log = LoggerFactory.getLogger(PushButtonHelper.class);

    private DigitalInput buttonInput;

    private final LEDHelper ledHelper;

    public PushButtonHelper(LEDHelper ledHelper,
                            @Named("button-input") DigitalInput buttonInput){
        this.ledHelper = ledHelper;
        this.buttonInput = buttonInput;
    }

    @PostConstruct
    public void initialize(){
        log.info("Initializing Push Button");

        buttonInput.addListener(e ->{
            if(buttonInput.isHigh()){
                System.out.println("PushButtonHelper, Led test, Led test");
                ledHelper.deviceOn();
            }else {
                ledHelper.deviceOff();
            }
        });
    }

    public void addEventListener(DigitalStateChangeListener function)  {
//        buttonInput.addListener(e -> {
//            try {
//                function.call();
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//        });
        buttonInput.addListener(function);
    }

    public void removeEventListener(DigitalStateChangeListener function){
//        buttonInput.removeListener(e -> {
//            try {
//                function.call();
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//        });
        buttonInput.removeListener(function);
    }
}
