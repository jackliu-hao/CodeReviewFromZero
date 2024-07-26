<script setup>

</script>

<template>
  <div>
    <input type="text" v-model="userInput" />
    <button @click="submit">提交</button>
    <p>{{ userInput }}</p>
  </div>
</template>

<script>
export default {

  data() {

    return {

      userInput: ''
    };
  },
  methods: {

    submit() {

      // 对用户输入进行转义，防止XSS攻击
      this.userInput = this.userInput
          .replace(/&/g, '&amp;')
          .replace(/</g, '&lt;')
          .replace(/>/g, '&gt;')
          .replace(/"/g, '&quot;')
          .replace(/'/g, '&#039;');
    }
  }
};
</script>

<style scoped>

</style>