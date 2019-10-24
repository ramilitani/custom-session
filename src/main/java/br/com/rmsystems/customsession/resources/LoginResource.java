package br.com.rmsystems.customsession.resources;

import br.com.rmsystems.customsession.dto.CustomSession;
import br.com.rmsystems.customsession.dto.UserLogin;
import br.com.rmsystems.customsession.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/*
    This resource is intended to group various services for the testing proposal
    simulating general actions in an e-commerce system.
    It was done this way because these services are beyond the scope of this project, so
    it doesn't follow the correct design patterns (to separate them into different classes).
    The resource that is the purpose of this project is named AuditResource.
 */
@RestController
public class LoginResource {

    @Autowired
    LoginService generalService;

    @PostMapping("/login")
    @ResponseBody
    CustomSession login(@Valid @RequestBody final UserLogin user, HttpSession session) {
        return generalService.login(user.getLogin(), user.getPassword(),session);
    }

    @GetMapping("/logout")
    void logout(HttpSession session) {
        generalService.logout(session);
    }
}
