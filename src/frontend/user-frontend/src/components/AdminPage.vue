<template>
  <div>
    <table>
      <tr v-for="user in users" :key="user.id">
        <td >
          {{ user.firstName }}
        </td>
      </tr>
    </table>
  </div>
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
</style>
