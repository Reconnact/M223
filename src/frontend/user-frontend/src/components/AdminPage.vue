<template>
  <div>
    <table>

    </table>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>First name</th>
        <th>Last name</th>
        <th>E-mail</th>
        <th>Role</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" :key="user.id" :id="user.id">
        <td >
          {{ user.id }}
        </td>
        <td >
          {{ user.firstName }}
        </td>
        <td >
          {{ user.lastName }}
        </td>
        <td >
          {{ user.email }}
        </td>
        <td >
          {{ user.role }}
        </td>
        <td><button class="delete-button" :id="user.email" @click="deleteUser(user.id)">Delete</button></td>
      </tr>
      </tbody>
    </table>
  </div>
  <button onclick="window.location.href = '/addUser'" >Add User</button>
</template>
<script>

import axios from "axios";

export default {
  name: 'AdminPage',
  data() {
    return {
      users: []
    };
  },
  async mounted() {
    const res = await axios.get('/api/v1/users/list');
    const data = await res.data;
    this.users = data;
  },
  methods: {
    deleteUser: async function  (id) {
      document.getElementById(id).style.display = "none ";
      try {
        const response = await axios.delete('/api/v1/users/' + id + "/delete");

        if (!response.status == 200) {
          throw new Error('Request failed');
        }
      } catch (error) {
        console.error(error);
      }
    }
  }

};

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}

table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}
th {
  background-color: #f2f2f2;
}
tr:hover {
  background-color: #f5f5f5;
}
.delete-button {
  background-color: #f44336;
  color: white;
  border: none;
  padding: 6px 12px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  margin: 2px 2px;
  cursor: pointer;
}
</style>
