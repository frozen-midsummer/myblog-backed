package com.wjx.controller;

import com.wjx.common.result.ApiResult;
import com.wjx.common.rpc.BaseService;
import com.wjx.dto.ValidateTokenQry;
import com.wjx.dto.LoginCmd;
import com.wjx.service.CustomUserDetailsService;
import com.wjx.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/token")
public class AuthenticateController extends BaseService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<ApiResult<String>> createAuthenticationToken(@RequestBody LoginCmd loginCmd) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCmd.getUsername(), loginCmd.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginCmd.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(ok(jwt));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<ApiResult> validateToken(HttpServletRequest request, @RequestBody ValidateTokenQry validateQry) throws Exception {
        try {
            String requestTokenHeader = request.getHeader("Authorization");
            String jwtToken = "";
            if (requestTokenHeader != null) {
                jwtToken = requestTokenHeader.substring(7);
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(validateQry.getUsername());
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                ApiResult res = ok();
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.ok(fail(401, "token 认证失败"));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(fail(e.hashCode(), e.getMessage()));
        }
    }
}
