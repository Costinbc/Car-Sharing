package carsharing;

public class Car {
        private String name;
        private int id;
        private int company_id;
        Car(String name, int id, int company_id){
            this.name = name;
            this.id = id;
            this.company_id = company_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCompanyId() {
        return company_id;
    }

        public void setCompanyId(int company_id) {
        this.company_id = company_id;
    }
}
