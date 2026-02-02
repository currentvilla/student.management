package raisetech.student.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

  @Autowired
  private StudentRepository repository;;

  private Map<String, String> student = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

  @GetMapping("/student")
  public String getStudent(@RequestParam String name) {
    Student student = repository.searchByName(name);
    return student.getName() + " " + student.getAge() + "歳";
  }

  @PostMapping("/student")
  public void registerStudent(@RequestParam String name,@RequestParam int age) {
    repository.registerStudent(name, age);
  }

  @PatchMapping("/student")
  public void updateStudent(@RequestParam String name,@RequestParam int age) {
    repository.updateStudent(name, age);
  }

  @DeleteMapping("/student")
  public void deleteStudent(String name) {
    repository.deleteStudent(name);
  }

  @GetMapping("/students")
  public List<Student> getAllStudents() {
    return repository.searchAllStudents();
  }

  @PostMapping("/studentMap")
  public void updateStudentMap(@RequestParam Map<String, String> student) {
    this.student.putAll(student);
  }

  @GetMapping("/studentMap")
  public Map<String, String> getStudentMap() {
    return student;
  }
}
