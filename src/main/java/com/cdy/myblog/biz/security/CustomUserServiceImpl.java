package com.cdy.myblog.biz.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: 2018/6/5 19:11
 * Describe: 用户登录处理
 */
@Service
public class CustomUserServiceImpl implements UserDetailsService{

//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

        return null;
//        User user = userRepository.findByPhone(phone);
//
//        if(user == null){
//            throw  new UsernameNotFoundException("用户不存在");
//        }
//
//        TimeUtil timeUtil = new TimeUtil();
//        String recentlyLanded = timeUtil.getFormatDateForSix();
//        userService.updateRecentlyLanded(user.getUsername(), recentlyLanded);
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        for(Role role : user.getRoles()){
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
