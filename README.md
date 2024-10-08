# TIL | 2024.09.14.
1. MVC 패턴에서 String(hello) 으로 리턴하면 해당하는 resources/template 아래에 있는 hello.html을 찾아 Model의 data를 보내준다.
2. @ResponseBody 어노테이션이 붙으면 return 타입을 그대로 리턴해준다.
   1) viewResolver 대신에 HttpMessageConverter가 동작한다.
   2) 문자열을 리턴하면 문자열을 그대로 리턴해주기 위해 StringHttpMessageConverter 가 동작한다.
   3) 객체를 리턴하면 MappingJackson2HttpMessageConverter가 동작한다.

# TIL | 2024.09.15.
1. @AfterEach
   테스트 코드를 작성할 때 각각의 테스트가 독립적일 수 있도록 작성하는 것이 중요하다. 순서에 의존적이면 안된다.
   테스트 코드를 작성하고 여러 테스트 코드를 한번에 돌리면, 다른 테스트 코드에 의해 Memory에 직전 테스트 코드의 DB 결과가 남아있어
   현재 테스트 코드에 영향을 줄 수 있다.
   이것을 방지하기 위해 테스트가 끝날 때마다 DB 정보를 지워주는 게 좋다.
   그래서 나는 '테스트 코드 끝에마다 clear해주는 코드를 호출해야겠구나'라고 생각했는데, 테스트 코드가 1만개가 되면 일일이 그걸 다 붙여주기 힘들다.
   (개발자는 게을러야 한다)
   그래서 `@AfterEach` 라는 어노테이션을 사용해서 테스트 코드가 끝날 때마다 수행할 메서드를 따로 작성해주면 간편하게 문제를 해결할 수 있다.
   ```java
   @AfterEach
    public void afterEach() {
        // 테스트 코드가 수행되고 나면 수행할 로직 작성
    }
   ```

2. `@BeforeEach`
   각 테스트 실행 전에 호출되는 어노테이션. </br>
   여기서는 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다.
   ```java
   @BeforeEach
     public void beforeEach() {
         memberRepository = new MemoryMemberRepository();
         memberService = new MemberService(memberRepository);
     }
   ```
   
# TIL | 2024.09.16.
1. `@Autowired` </br>
   ```java
   public class ClassA {
       private final ClassB classB;
       
      @Autowired
       public ClassA(ClassB classB) {
           this.classB = classB;
       }
   }
   ```
   `@Autowired` 어노테이션이 붙어있으면 ClassA 객체 생성 시점에 스프링 컨테이너가 ClassB 스프링 빈을 찾아서 주입한다. </br>
   생성자가 한 개 뿐이라면 **생략할 수 있다.**
2. 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때 싱글톤으로 등록한다. 즉 하나의 인스턴스를 생성해서 계속 사용한다.
   (싱글톤이 아니게 설정할 수 있지만, 대부분 싱글톤으로 사용한다.)
3. `@SpringBootApplication` </br>
   스프링 컨테이너와 테스트를 함께 실행한다
4. `@Transactional` </br>
   테스트 시작 전에 트랜잭션을 실행하고, 테스트 종료후에 롤백을 하기 때문에, DB에 영향없이 테스트를 진행할 수 있다.