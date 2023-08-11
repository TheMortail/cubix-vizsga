package hu.cubix.spring.gyuri.logistics.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "transport-plans")
@Component
public class TransportPlansProperties {

    private Decrease decrease;
    public static class Decrease{
        private Long value1;
        private Long value2;
        private Long value3;
        private Long value4;

        public Long getValue1() {
            return value1;
        }

        public void setValue1(Long value1) {
            this.value1 = value1;
        }

        public Long getValue2() {
            return value2;
        }

        public void setValue2(Long value2) {
            this.value2 = value2;
        }

        public Long getValue3() {
            return value3;
        }

        public void setValue3(Long value3) {
            this.value3 = value3;
        }

        public Long getValue4() {
            return value4;
        }

        public void setValue4(Long value4) {
            this.value4 = value4;
        }
    }

    public Decrease getDecrease() {
        return decrease;
    }

    public void setDecrease(Decrease decrease) {
        this.decrease = decrease;
    }
}
