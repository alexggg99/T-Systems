/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tsystems.project.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import ru.tsystems.project.domain.DAO.interfaces.PassengerDAO;
import ru.tsystems.project.domain.entities.Passenger;
import ru.tsystems.project.exceptions.CustomDAOException;

/**
 * Spring security class needed for authorisation.
 */
@Service("userDetailsService")
public class AppUserDetailsServiceDAO implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AppUserDetailsServiceDAO.class);

    @Autowired
    private PassengerDAO passengerDAO;

    /**
     * This method override the base security method and sets the password and
     * the role of the user to Spring Security user.
     *
     * @param username the user's login;
     * @return org.springframework.security.core.userdetails.User
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Passenger passenger;
        try {
            passenger = passengerDAO.getPassengerByLastName(username);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(passenger.getRole().getName()));
            return new org.springframework.security.core.userdetails.User(username, passenger.getPassword(), true, true, true, true, authorities);
        } catch (CustomDAOException ex) {
            throw new UsernameNotFoundException(username + " not found");
        } catch (Exception ex) {
            logger.error("Exception caught: " + ex);
            return null;
        }

    }
}
