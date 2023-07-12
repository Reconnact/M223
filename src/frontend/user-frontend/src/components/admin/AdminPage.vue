<template>
  hello
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
    },
    editUser: async function  (id) {
      window.location.href = "/editUser/" + id
    }
  }

};
</script>