package coder.aihui.data.normalbean;

import java.io.Serializable;

/**
 * @ 创建者   zhou
 * @ 创建时间   2017/7/21 9:47
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2017/7/21$
 * @ 更新描述  ${TODO}
 */


    public   class  PartsBean implements Serializable {
    private static final long serialVersionUID = -653365335148918726L;
    /**
         * BXJZRQ : 2017-05-31
         * HTMXID : 262
         * ID : 2
         * PD_BRAND : 2
         * PD_BXQ :
         * PD_CODE : 2
         * PD_GGXH : 2
         * PD_NUM : 2
         * PD_PJMC : 配件B
         * PD_PRICE : 2
         * PD_REMARK : 2
         * PD_UNIT : 2
         */

        private String BXJZRQ;
        private int    HTMXID;
        private int    ID;
        private String PD_BRAND;
        private String PD_BXQ;
        private String PD_CODE;
        private String PD_GGXH;
        private int    PD_NUM;
        private String PD_PJMC;
        private int    PD_PRICE;
        private String PD_REMARK;
        private String PD_UNIT;

        public String getBXJZRQ() {
            return BXJZRQ;
        }

        public void setBXJZRQ(String BXJZRQ) {
            this.BXJZRQ = BXJZRQ;
        }

        public int getHTMXID() {
            return HTMXID;
        }

        public void setHTMXID(int HTMXID) {
            this.HTMXID = HTMXID;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getPD_BRAND() {
            return PD_BRAND;
        }

        public void setPD_BRAND(String PD_BRAND) {
            this.PD_BRAND = PD_BRAND;
        }

        public String getPD_BXQ() {
            return PD_BXQ;
        }

        public void setPD_BXQ(String PD_BXQ) {
            this.PD_BXQ = PD_BXQ;
        }

        public String getPD_CODE() {
            return PD_CODE;
        }

        public void setPD_CODE(String PD_CODE) {
            this.PD_CODE = PD_CODE;
        }

        public String getPD_GGXH() {
            return PD_GGXH;
        }

        public void setPD_GGXH(String PD_GGXH) {
            this.PD_GGXH = PD_GGXH;
        }

        public int getPD_NUM() {
            return PD_NUM;
        }

        public void setPD_NUM(int PD_NUM) {
            this.PD_NUM = PD_NUM;
        }

        public String getPD_PJMC() {
            return PD_PJMC;
        }

        public void setPD_PJMC(String PD_PJMC) {
            this.PD_PJMC = PD_PJMC;
        }

        public int getPD_PRICE() {
            return PD_PRICE;
        }

        public void setPD_PRICE(int PD_PRICE) {
            this.PD_PRICE = PD_PRICE;
        }

        public String getPD_REMARK() {
            return PD_REMARK;
        }

        public void setPD_REMARK(String PD_REMARK) {
            this.PD_REMARK = PD_REMARK;
        }

        public String getPD_UNIT() {
            return PD_UNIT;
        }

        public void setPD_UNIT(String PD_UNIT) {
            this.PD_UNIT = PD_UNIT;
        }

        @Override
        public String toString() {
            return "{" +
                    "BXJZRQ='" + BXJZRQ + '\'' +
                    ", HTMXID=" + HTMXID +
                    ", ID=" + ID +
                    ", PD_BRAND='" + PD_BRAND + '\'' +
                    ", PD_BXQ='" + PD_BXQ + '\'' +
                    ", PD_CODE='" + PD_CODE + '\'' +
                    ", PD_GGXH='" + PD_GGXH + '\'' +
                    ", PD_NUM=" + PD_NUM +
                    ", PD_PJMC='" + PD_PJMC + '\'' +
                    ", PD_PRICE=" + PD_PRICE +
                    ", PD_REMARK='" + PD_REMARK + '\'' +
                    ", PD_UNIT='" + PD_UNIT + '\'' +
                    '}';
        }

}
