<template>
  <div class="form">
        <h3>SignUp</h3>
        <label for="username">User Name</label>
        <input type="text" name="username"  required v-model="username">
        <label for="password">Password</label>
        <input type="password" name="password" required v-model="password">
        <label for="roles">Role</label>
        <input type="text" name="roles" required v-model="roles">
        <button @click="SignUp" class="SignUp">SignUp</button>
  </div>
</template>

<script>
import jwt_decode from 'jwt-decode';

export default {
name: "SignUp", 
data: function() {
    return {
      username: '',
      password: '',
      roles: '', 
      token:'',
      decodedToken: ''
    }
  },
  methods: {
  async SignUp() {
      var data = {
        name: this.username,
        password: this.password,
        roles: this.roles
      };
      
      // using Fetch - post method - send an HTTP post request to the specified URI with the defined body
      await fetch("http://localhost:8090/api/auth/signup", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
          credentials: 'include',
          body: JSON.stringify(data),
      })
      .then(response => response.text())
      .then(response => {
        //saving the jwt in the token variable
        this.token = response;
        if (this.token.startsWith("ey")){
        //decoding the jwt and save it in the decodedToken variable
        this.decodedToken = jwt_decode(this.token);
        // saving the returned user role into the roles variable
        this.roles = this.decodedToken.roles
        console.log(this.decodedToken.roles);
        // saving the token into the windows local storage 
        localStorage.setItem('jwtToken',  this.token);
        console.log(localStorage.getItem('jwtToken'));
        this.$router.push("/");
      }}) 
      .catch((e) => {
        console.log(e);
        console.log("error");
      });
    },
  }, 
  }
</script>

<style scoped>
.form {
  max-width: 420px;
  margin: 30px auto;
  background: rgb(167, 154, 154);
  text-align: left;
  padding: 40px;
  border-radius: 10px;
}
h3 {
  text-align: center;
  color: rgb(8, 110, 110);
}
label {
  color: rgb(8, 110, 110);
  display: inline-block;
  margin: 25px 0 15px;
  font-size: 0.8em;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bold;
}
input {
  display: block;
  padding: 10px 6px;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid white;
  color: blue;
}
button {
  background: rgb(8, 110, 110);
  border: 0;
  padding: 10px 20px;
  margin-top: 20px;
  color: white;
  border-radius: 20px;
  align-items: center;
  text-align: center;
}
</style>