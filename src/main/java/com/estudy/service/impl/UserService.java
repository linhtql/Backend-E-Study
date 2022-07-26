package com.estudy.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudy.config.TimeConfig;
import com.estudy.entities.User;
import com.estudy.form.EditUserForm;
import com.estudy.form.RegisterForm;
import com.estudy.model.UserInfo;
import com.estudy.repository.UserRepository;
import com.estudy.service.IUserService;
import com.estudy.utils.CustomUserDetails;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StorageService storageService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Not found : " + username);
        }
        return new CustomUserDetails(user);
    }

    @Override
    public UserDetails loadUserById(long id) {
        User user = userRepository.findById(id);
        return new CustomUserDetails(user);
    }

    @Override
    public UserInfo getByUserName(String username) {
        User user = userRepository.findByUsername(username);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setUsername(user.getUsername());
        userInfo.setBirthOfDate(user.getBirthOfDate());
        userInfo.setPhone(user.getPhone());
        userInfo.setEmail(user.getEmail());
        userInfo.setAddress(user.getAddress());
        userInfo.setAvatar(user.getAvatar());

        return userInfo;
    }

    @Override
    public UserInfo register(RegisterForm registerForm) {
        User user1 = userRepository.findByUsername(registerForm.getUsername());
        if (user1 == null) {
            System.out.println("da vao null");
            user1 = new User();
            user1.setFirstName(registerForm.getFirstName());
            user1.setLastName(registerForm.getLastName());
            user1.setUsername(registerForm.getUsername());
            user1.setPassword(passwordEncoder.encode(registerForm.getPassword()));
            user1.setBirthOfDate(registerForm.getBirthOfDate());
            user1.setPhone(registerForm.getPhone());
            user1.setEmail(registerForm.getEmail());
            user1.setAddress(registerForm.getAddress());
            String avatar;
            if (registerForm.getAvatar().isEmpty()) {
                avatar = "https://res.cloudinary.com/dxultkptn/image/upload/v1658150890/default_nbaeby.jpg";
            } else {
                avatar = storageService.storageFile(registerForm.getAvatar());
            }
            user1.setAvatar(avatar);
            user1.setCreatedDate(new Date());

            User user = userRepository.save(user1);
            UserInfo userInfo = information(user.getId());

            return userInfo;

        } else {
            return null;
        }

    }

    @Override
    public UserInfo information(long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return null;
        } else {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setFirstName(user.getFirstName());
            userInfo.setLastName(user.getLastName());
            userInfo.setBirthOfDate(user.getBirthOfDate());
            userInfo.setPhone(user.getPhone());
            userInfo.setEmail(user.getEmail());
            userInfo.setAddress(user.getAddress());
            userInfo.setAvatar(user.getAvatar());
            userInfo.setDateCreated(TimeConfig.getTime(user.getCreatedDate()));

            return userInfo;

        }

    }

    @Override
    public UserInfo updateUser(RegisterForm registerForm, Long id) {

        User oldUser = userRepository.findOneById(id);
        if (oldUser != null) {
            oldUser.setFirstName(registerForm.getFirstName());
            oldUser.setLastName(registerForm.getLastName());
            oldUser.setBirthOfDate(registerForm.getBirthOfDate());
            oldUser.setPhone(registerForm.getPhone());
            oldUser.setEmail(registerForm.getEmail());
            oldUser.setAddress(registerForm.getAddress());
            String avatar;
            if (registerForm.getAvatar().isEmpty()) {
                avatar = oldUser.getAvatar();
            } else {
                avatar = storageService.storageFile(registerForm.getAvatar());

            }
            oldUser.setAvatar(avatar);
            oldUser.setModifiedDate(new Date());
            oldUser.setRoleId(registerForm.getRoleId());

            User user = userRepository.save(oldUser);

            UserInfo userInfo = information(user.getId());

            return userInfo;
        }
        return null;
    }

    @Override
    public UserInfo editUser(EditUserForm editUserForm, Long id) {
        User oldUser = userRepository.findOneById(id);
        if (oldUser != null) {
            oldUser.setActive(editUserForm.isActive());
            oldUser.setRoleId(editUserForm.getRoleId());
            User user = userRepository.save(oldUser);

            UserInfo userInfo = information(user.getId());
            return userInfo;
        }
        return null;
    }

    @Override
    public UserInfo convertToUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setUsername(user.getUsername());
        userInfo.setBirthOfDate(user.getBirthOfDate());
        userInfo.setPhone(user.getPhone());
        userInfo.setEmail(user.getEmail());
        userInfo.setAddress(user.getAddress());
        userInfo.setAvatar(user.getAvatar());
        return userInfo;
    }

}
