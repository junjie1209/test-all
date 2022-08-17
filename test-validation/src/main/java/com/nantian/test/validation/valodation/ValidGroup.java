package com.nantian.test.validation.valodation;

import javax.validation.groups.Default;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/8/17
 */
public interface ValidGroup extends Default {
    interface Crud extends ValidGroup {
        interface Create extends Crud {
        }

        interface Update extends Crud {
        }

        interface Query extends Crud {
        }

        interface Delete extends Crud {
        }
    }
}
