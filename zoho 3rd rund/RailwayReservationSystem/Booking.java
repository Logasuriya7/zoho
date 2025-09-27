public class Booking {
    int Bookingid;
    String name;
    int age;
    String gender;
    String bearth;


    public Booking(int Bookingid,String name, int age, String gender, String bearth) {
        this.Bookingid = Bookingid;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bearth = bearth;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "Bookingid=" + Bookingid +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", bearth='" + bearth + '\'' +
                '}';
    }
}
