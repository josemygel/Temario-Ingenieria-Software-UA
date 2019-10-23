export class Servicio_API { 
    constructor(url) {
        this.API_URL = url
    }
    
    openThread(_id) {   
        alert("Thread pulsado: " + _id);
        return getThread(_id);
    }
        
    getThreads() {
        return fetch(this.API_URL + 'threads/')
            .then(function(response) {
                if (response.ok)
                    return response.json()
            })
    }

    getThread(id) {
       return fetch(this.API_URL+'threads/'+id)
            .then(function(response) {
                if (response.ok)
                    return response.json()
            })
    }

    deleteThread(id) {
        localStorage.token
       return fetch(this.API_URL+'threads/'+id, {
        method: 'DELETE',
        headers: {
            'Content-type':'application/json',
            'Authorization':localStorage.token
        }
        }).then(function(response) {
            return response.json()
        })
    }
    createThread(thread) {
       return fetch(this.API_URL+'threads/', {
        method: 'POST',
        headers: {
            'Content-type':'application/json',
            'Authorization':localStorage.token
        },
        body: JSON.stringify(thread)
        }).then(function(response) {
                    return response.json()
        })
    }

    obtenerItems() {
        return fetch(this.API_URL)
            .then(function(response) {
                if (response.ok)
                    return response.json()
            })
    }

    addItem(item) {
        return fetch(this.API_URL, {
                   method: 'POST',
                   headers: {
                       'Content-type':'application/json'
                   },
                   body: JSON.stringify(item)
               }).then(function (respuesta) {
                   if (respuesta.ok)
                      return respuesta.json()
               })
    }

    getItem(id) {
       return fetch(this.API_URL+'/'+id)
            .then(function(response) {
                if (response.ok)
                    return response.json()
            })
    }

    getMe(token) {
        if(!token) return false;

        return fetch(this.API_URL+'me',{
            method: 'GET',
            headers: {
                'Content-type':'application/json',
                'Authorization':token
            }
        }).then(function(response) {
            if(response.ok)
                return response.json();
            else return false;
        })
    }

    login(cuenta) {
        return fetch(this.API_URL+'login',{
            method: 'POST',
            headers: {
                'Content-type':'application/json'
            },
            body: JSON.stringify(cuenta)
        }).then(function(response) {
            return response.json()
        })
    }
    register(cuenta) {
        localStorage.token = undefined;
        return fetch(this.API_URL+'register',{
            method: 'POST',
            headers: {
                'Content-type':'application/json'
            },
            body: JSON.stringify(cuenta)
        }).then(function(response) {
            return response.json()
        })
    }
}
