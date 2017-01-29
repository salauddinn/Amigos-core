package com.myworld.connect.serviceTests;

import com.myworld.connect.model.User;
import com.myworld.connect.repositories.UserRepository;
import com.myworld.connect.services.UserNotFoundException;
import com.myworld.connect.services.UserService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldUpdateThePasswordOfExistingUser() throws UserNotFoundException {
        User existingUser = new User(1, "sample@email.com", "idontknow");
        when(userRepository.findByEmailID("sample@email.com")).thenReturn(existingUser);
        User testUser = new User();
        testUser.setPassword("iknow");
        userService.update(testUser, "sample@email.com");
        Assert.assertEquals(existingUser.getPassword(), "iknow");
        verify(userRepository).save(existingUser);
    }

    @Test
    public void shouldThrowExceptionIfUserDoesntExist() throws UserNotFoundException {
        when(userRepository.findByEmailID("unknown@email.com")).thenReturn(null);
        User testUser = new User();
        testUser.setPassword("iknow");
        expectedException.expect(UserNotFoundException.class);
        expectedException.expectMessage("User doesn't exist");
        userService.update(testUser, "unknown@email.com");
    }

    @Test
    public void shouldDeleteExistingUser() throws UserNotFoundException {
        User existingUser = new User(1, "sample@email.com", "idontknow");
        when(userRepository.findByEmailID("sample@email.com")).thenReturn(existingUser);
        userService.delete("sample@email.com");
        verify(userRepository).delete(existingUser.getUserID());
    }

    @Test
    public void shouldThrowExceptionIfUserDoesntExistWhenDeleteIsCalled() throws UserNotFoundException {
        when(userRepository.findByEmailID("unknown@email.com")).thenReturn(null);
        expectedException.expect(UserNotFoundException.class);
        expectedException.expectMessage("User doesn't exist");
        userService.delete("unknown@email.com");
    }

}