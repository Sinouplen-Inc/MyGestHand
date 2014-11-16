'use strict';

mygesthandJhipsterApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
        $routeProvider
            .when('/contact', {
                templateUrl: 'views/contacts.html',
                controller: 'ContactController',
                resolve: {
                    resolvedContact: ['Contact', function (Contact) {
                        return Contact.query().$promise;
                    }],
                    resolvedUser: ['User', function (User) {
                        return User.query().$promise;
                    }]
                },
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            })
    });
