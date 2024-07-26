



<script>
import {fetchHelloMessage} from "@/api/hello.js";
import DOMPurify from 'dompurify';

export default {
  data() {
    return {
      msg: '',
      img: '',
      click:''
    }
  },
  methods: {
    changeMessage() {
      this.msg = 'Hello, World!'
    },
    async fetchData (){
      try {
        // 使用js  获取当前的query
        const userName = new URLSearchParams(window.location.search).get('userName');
        const img = new URLSearchParams(window.location.search).get('img');
        const click = new URLSearchParams(window.location.search).get('click');
        const data = { userName: userName ,img: img,click: click}; // 示例参数
        console.log(data)
        const response = await fetchHelloMessage(data)
        console.log(response);
        this.msg =  response.userName;
        this.img =  response.img;
        this.click =  response.click;
        // 修复v-html引起的XSS
        this.msg =  DOMPurify.sanitize(this.msg);
        console.log(this.msg )
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
  },
  mounted() {
    this.fetchData();
  }
}
</script>

<template>
  <div class="greetings">
<!--    这种写法是不存在XSS的，即使后端未做任何处理 -->
    <h1 class="green">{{ msg }}</h1>
<!--    使用v-html 会把数据直接当成html渲染出来，那这个时候就没办法进行编码处理了 -->
    <div v-html="msg"></div>

<!--    动态插入属性-->
    <img :src="img" />
<!--    <a :href="click">Click me</a>-->
    <button @click="click">Click me</button>

    <h3>
      You’ve successfully created a project with
      <a href="https://vitejs.dev/" target="_blank" rel="noopener">Vite</a> +
      <a href="https://vuejs.org/" target="_blank" rel="noopener">Vue 3</a>.
    </h3>
  </div>
</template>



<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  position: relative;
  top: -10px;
}

h3 {
  font-size: 1.2rem;
}

.greetings h1,
.greetings h3 {
  text-align: center;
}

@media (min-width: 1024px) {
  .greetings h1,
  .greetings h3 {
    text-align: left;
  }
}
</style>

