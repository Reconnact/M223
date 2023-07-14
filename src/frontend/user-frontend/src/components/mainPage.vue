<template>
  main
</template>
<script>

import axios from "axios";

export default {
  name: 'MainPage',
  mounted() {
    if (localStorage.getItem("user") == null) {
      window.location.href = "/login";
    }
  },
};

</script>
