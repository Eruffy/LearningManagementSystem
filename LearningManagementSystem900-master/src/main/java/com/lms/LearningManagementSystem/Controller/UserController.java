package com.lms.LearningManagementSystem.Controller;

import com.lms.LearningManagementSystem.Model.User.LoginRequest;
import com.lms.LearningManagementSystem.Model.User.User;
import com.lms.LearningManagementSystem.Service.UserService.InstructorService;
import com.lms.LearningManagementSystem.Service.UserService.StudentService;
import com.lms.LearningManagementSystem.Service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.lms.LearningManagementSystem.Service.UserService.UserService.userStore;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService; // Use the interface instead of the implementation

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        User authenticatedUser = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (authenticatedUser != null) {
            return new ResponseEntity<>("Login successful for user: " + authenticatedUser.getName(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User createdUser = userService.addUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @PutMapping("/{id}")
    public User updateUser(User updateUser, Long id) {
        User existingUser = userStore.get(id);
        if (existingUser != null) {
            if (updateUser.getName() != null) {
                existingUser.setName(updateUser.getName());
            }
            if (updateUser.getEmail() != null) {
                existingUser.setEmail(updateUser.getEmail());
            }
            if (updateUser.getPassword() != null) {
                existingUser.setPassword(updateUser.getPassword());
            }
            if (updateUser.getRole() != null) {
                existingUser.setRole(updateUser.getRole());
            }
            return existingUser;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers(
            @RequestParam(required = false) String role) {

        try {
            ArrayList<User> filteredUsers = userService.listUsers(role);
            return new ResponseEntity<>(filteredUsers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

   @PostMapping("/{userId}/enroll")//fixed 
    public ResponseEntity<?> enrollInCourse(
            @PathVariable Long userId,
            @RequestParam String courseId) {
        try {
            boolean success = StudentService.enrollInCourse(userId, courseId);
            if (success) {
                return new ResponseEntity<>("Student successfully enrolled to the course.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Operation failed: Course does not exist.", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Operation failed: You are not an student.", HttpStatus.BAD_REQUEST);
        }
    }

    // Assign instructor to a course
    @PostMapping("/{instructorId}/assign/{courseId}") //fixed
    public ResponseEntity<String> assignInstructorToCourse(
            @PathVariable Long instructorId,
            @PathVariable String courseId) {
        try {
            boolean success = InstructorService.assignInstructorToCourse(instructorId, courseId);
            if (success) {
                return new ResponseEntity<>("Instructor successfully assigned to the course.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Operation failed: Course does not exist.", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Operation failed: You are not an Instructor.", HttpStatus.BAD_REQUEST);
        }
    }

    // Instructor generates OTP for a lesson
    @PostMapping("/generate-otp/{instructorId}/{courseId}/{lessonId}")
    public ResponseEntity<String> generateOtpForLesson(
            @PathVariable Long instructorId,
            @PathVariable String courseId,
            @PathVariable String lessonId) {
        try {
            String otp = InstructorService.generateOtpForLesson(instructorId, courseId, lessonId);
            return new ResponseEntity<>("Generated OTP successfully: " + otp, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Operation failed: You are not an Instructor.", HttpStatus.BAD_REQUEST);
        }
    }

    // Student enters OTP to mark attendance
    @PostMapping("/mark-attendance/{studentId}/{courseId}/{lessonId}")
    public ResponseEntity<String> markAttendance(@PathVariable Long studentId, @PathVariable String courseId, @PathVariable String lessonId,
                                                 @RequestParam String otp) {
        try {
            boolean result = StudentService.markAttendance(studentId, courseId, lessonId, otp);
            if (result) {
                return new ResponseEntity<>("Attendance marked successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to mark attendance.", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Operation failed: You are not an Student.", HttpStatus.BAD_REQUEST);
        }

    }
}
