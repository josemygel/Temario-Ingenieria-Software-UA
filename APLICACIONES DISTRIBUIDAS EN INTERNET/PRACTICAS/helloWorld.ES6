class User {
    constructor(name,sex,years,isAdmin)
    {
        this.name = name,
        this.sex = sex,
        this.years = years,
        this.isAdmin = isAdmin
    };

    toString()
    {
        let stringType;
        this.isAdmin === true ? stringType = "Admin" : stringType = "Anonyuser";
        
        return this.name + " " + this.sex + " " + this.years + " and is a " + stringType;
    };
};

class Admin extends User{
    adminFuncion()
    {
        return "Soy el genial administrador que se aburre =D";
    }
}

var a = new User("4N0N1M0U5","Varon",23,false);
//console.log(a.toString());
//console.log(a);

var admin = new Admin("n04n0n1m0u5","OVERFLOW",-23,true);
//console.log(admin.toString());
//console.log(admin);

console.log(a)
console.log(admin)