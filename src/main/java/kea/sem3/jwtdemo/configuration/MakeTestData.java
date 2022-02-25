package kea.sem3.jwtdemo.configuration;

import kea.sem3.jwtdemo.entity.*;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.Month;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {


    UserRepository userRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public MakeTestData(UserRepository userRepository, MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public  void makePlainUsers(){
        BaseUser user = new BaseUser("user", "user@a.dk", "test12");
        user.addRole(Role.USER);
        BaseUser admin = new BaseUser("admin", "admin@a.dk", "test12");
        admin.addRole(Role.ADMIN);

        BaseUser both = new BaseUser("user_admin", "both@a.dk", "test12");
        both.addRole(Role.USER);
        both.addRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(both);

        //Member m1 = new Member("xxx","xxx@a.dk","test123","Mark","Hansen","xxx","yyyy",2200);
        //m1.addRole(Role.USER);
        //memberRepository.save(m1);

        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("#################################### WARNING ! #########################################");
        System.out.println("## This part breaks a fundamental security rule -> NEVER ship code with default users ##");
        System.out.println("########################################################################################");
        System.out.println("########################  REMOVE BEFORE DEPLOYMENT  ####################################");
        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("Created TEST Users");

    }

    public void makeMembers(){
        Member member1 = new Member("memb1", "memb1@mail.dk", "123", "Albert", "Ein", "Street1", "by1", 2200);
        Member member2 = new Member("memb2", "memb2@mail.dk", "123", "John", "Einst", "Street1", "by2", 2250);
        Member member3 = new Member("memb3", "memb3@mail.dk", "123", "Wilfred", "EinStein", "Street1", "by3", 2100);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
    }

    public void makeCars(){
        Car car1 = new Car("Brand1","model1",200,20);
        Car car2 = new Car("Brand2","model2",250,20);
        Car car3 = new Car("Brand2","model2",250,10);

        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);

    }

    public void makeReservations(){
        Reservation reservation = new Reservation(LocalDate.of(2022,2,16),carRepository.findById(1).get(),memberRepository.findById("memb1").get());
        reservationRepository.save(reservation);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        reservationRepository.deleteAll();
        userRepository.deleteAll();
        carRepository.deleteAll();
        makePlainUsers();
        makeCars();
        makeMembers();
        makeReservations();

    }
}
