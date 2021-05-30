'use strict';

class UserStorage {

    loginUser(id, password) {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                if (
                    (id === 'ellie' && password === 'dream') ||
                    (id === 'coder' && password === 'academy')
                ) {
                    resolve(id);

                } else {
                    reject(new Error('not found'))
                }
            }, 2000);
        });
    }

    getRoles(user) {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                if (user === 'ellie') {
                    resolve({
                        name: 'ellie',
                        role: 'admin'
                    })
                } else {
                    reject(new Error('no athroized'));
                }
            }, 1000);
        })
    }
}





const userStorage = new UserStorage();
userStorage.loginUser('ellie', 'dream')
    .then(userStorage.getRoles)
    .then((result) => {
        alert(`hi ${result.name} and you hava a ${result.role} of role`);

    }).catch((err) => {
        alert(err);
    });