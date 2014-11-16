'use strict';

mygesthandJhipsterApp.factory('Contact', function ($resource) {
    return $resource('app/rest/contacts/:id', {}, {
        'query': {method: 'GET', isArray: true},
        'get': {method: 'GET'}
    });
});
