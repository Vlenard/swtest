package com.anonymous;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    // --- 1. Feladatrész: Kezdeti állapot ellenőrzése ---

    @Test
    @DisplayName("A létrehozott user objektum nem null")
    void testUserNotNull() {
        assertNotNull(user);
    }

    @Test
    @DisplayName("A username és password mezők kezdeti értéke null")
    void testUsernameAndPasswordNullByDefault() {
        assertAll(
            () -> assertNull(user.getUserName()),
            () -> assertNull(user.getUsername()),
            () -> assertNull(user.getPassword())
        );
    }

    @Test
    @DisplayName("Az id és a loginCount mezők kezdeti értéke 0")
    void testIdAndLoginCountZeroByDefault() {
        assertAll(
            () -> assertEquals(0, user.getId()),
            () -> assertEquals(0, user.getLoginCount())
        );
    }

    @Test
    @DisplayName("Az updatePwd metódus hívása hibát dob (UnsupportedOperationException)")
    void testUpdatePwdThrowsException() {
        assertThrows(
            UnsupportedOperationException.class,
            () -> user.updatePwd("ujJelszo123", user.isLoggedIn())
        );
    }

    // --- 2. Feladatrész: TDD bővítés (isLoggedIn, login, logout, getLoginCount) ---

    @Test
    @DisplayName("Kezdetben az isLoggedIn false értéket ad vissza")
    void testIsLoggedInInitiallyFalse() {
        assertFalse(user.isLoggedIn());
    }

    @Test
    @DisplayName("login() meghívása után isLoggedIn true, és a loginCount értéke 1")
    void testLoginSetsIsLoggedInTrueAndIncrementsLoginCount() {
        user.login();

        assertAll(
            () -> assertTrue(user.isLoggedIn()),
            () -> assertEquals(1, user.getLoginCount())
        );
    }

    @Test
    @DisplayName("Többszöri login() hívás megfelelően növeli a loginCount mező értékét")
    void testMultipleLoginsIncrementLoginCount() {
        user.login();
        user.login();
        user.login();

        assertEquals(3, user.getLoginCount());
        assertTrue(user.isLoggedIn());
    }

    @Test
    @DisplayName("logout() meghívása után az isLoggedIn állapota false-ra változik")
    void testLogoutSetsIsLoggedInFalse() {
        user.login();
        assertTrue(user.isLoggedIn());

        user.logout();
        assertFalse(user.isLoggedIn());
        // A loginCount nem csökken a kijelentkezéssel
        assertEquals(1, user.getLoginCount());
    }

    @Test
    @DisplayName("Kijelentkezés utáni ismételt bejelentkezés működik és növeli a loginCount-ot")
    void testLoginAfterLogout() {
        user.login();
        assertEquals(1, user.getLoginCount());

        user.logout();
        assertFalse(user.isLoggedIn());

        user.login();
        assertTrue(user.isLoggedIn());
        assertEquals(2, user.getLoginCount());
    }

    @Test
    @DisplayName("Getter és setter metódusok működése (userName, password, id)")
    void testGettersAndSetters() {
        user.setUserName("tesztElek");
        user.setPassword("titok123");
        user.setId(42);

        assertAll(
            () -> assertEquals("tesztElek", user.getUserName()),
            () -> assertEquals("titok123", user.getPassword()),
            () -> assertEquals(42, user.getId())
        );
    }
}
