package com.tw;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PrimaryLockerRobotTest {
    @Test
    public void should_return_ticket_and_store_in_1st_locker_when_store_bag_given_robot_manage_two_lockers_and_both_has_capacity() throws Throwable {
        Locker firstLocker = new Locker(10);
        PrimaryLockerRobot robot = new PrimaryLockerRobot(Arrays.asList(firstLocker, new Locker(15)));
        Bag myBag = new Bag();
        Ticket ticket = robot.store(myBag);

        assertNotNull(ticket);
        Bag bag = firstLocker.fetch(ticket);
        assertEquals(myBag, bag);
    }

    @Test
    public void should_return_ticket_and_store_in_2st_locker_when_store_bag_given_robot_manage_two_lockers_and_1st_is_full_and_2nd_has_capacity() throws Throwable {
        Locker secondLocker = new Locker(15);
        PrimaryLockerRobot robot = new PrimaryLockerRobot(Arrays.asList(new Locker(0), secondLocker));
        Bag myBag = new Bag();
        Ticket ticket = robot.store(myBag);

        assertNotNull(ticket);
        Bag bag = secondLocker.fetch(ticket);
        assertEquals(myBag, bag);
    }

    @Test
    public void should_prompt_failure_when_store_bag_given_robot_manage_two_lockers_and_both_are_full() throws Throwable {
        PrimaryLockerRobot robot = new PrimaryLockerRobot(Arrays.asList(new Locker(0), new Locker(0)));
        Bag myBag = new Bag();
        assertThrows(LockerAlreadyFullException.class, () -> robot.store(myBag));
    }
}
