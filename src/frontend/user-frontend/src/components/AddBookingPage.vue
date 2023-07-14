<template>
  <div class="adduser-box">
    <h2>Add Booking</h2>
    <form>
      <div class="user-box">
        <input type="date" id="date" :value="date" required>
      </div>
      <div class="user-box" >
        <span for="halfday">Full day</span><br>
        <input type="radio" id="fullDay" name="duration" value="true" style="margin: 0" checked>
        <br><br>
        <span for="halfday">Half day</span><br>
        <input type="radio" id="halfday" name="duration" value="false" style="margin: 0">
      </div>
      <a @click="addBooking()">
        <span></span>
        Submit
      </a>
    </form>
  </div>
</template>
<script>

import axios from "axios";
let config = {
  headers: {
    Authorization: localStorage.getItem("user"),
  }
}
export default {
  name: 'AddBookingPasge',
  data() {
    return {
      date: new Date().toISOString().split('T')[0]
    };
  },
  mounted() {
    if (localStorage.getItem("user") == null) {
      window.location.href = "/login";
    }
  },
  methods: {
    addBooking: async function  () {
      console.log(document.getElementById("date").value)
      try {
        const response = await axios({
          method: 'post',
          url: "/api/v1/bookings/add",
          headers: {
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem("user")
          },
          data: {
            "date": document.getElementById("date").value,
            "isFullDay": document.getElementById("fullDay").checked,
          }
        });

        if (!response.status == 200) {
          throw new Error('Request failed');
        } else {
          if ('referrer' in document) {
            window.location = document.referrer;
          } else {
            window.history.back();
          }
        }
      } catch (error) {
        console.error(error);
      }
    }
  }
};

</script>

<style>


</style>
