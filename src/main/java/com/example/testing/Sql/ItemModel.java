package com.example.testing.Sql;

public class ItemModel {


        private String id;
        private String code;
        private String des;
        private String quan;

    // constructor
    public ItemModel(String id,
                     String code,
                     String des,
                     String quan)
    {
        this.id = id;
        this.code = code;
        this.des = des;
        this.quan = quan;
    }


        // creating getter and setter methods
        public String getItemCode() { return code; }

//        public void setItemCode(String code)
//        {
//            this.code = code;
//        }

        public String getItemDes()
        {
            return des;
        }

//        public void setItemDes(String des)
//        {
//            this.des = des;
//        }

        public String getItemQuan() { return quan; }

//        public void setItemQuan(String quan)
//        {
//            this.quan = quan;
//        }
//

        public String getId() { return id; }

//        public void setId(String id) { this.id = id; }




}
