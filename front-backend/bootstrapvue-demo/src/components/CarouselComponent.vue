<template>
  <div>
    <b-carousel
      id="carousel-fade"
      style="text-shadow: 0px 0px 2px #000"
      fade
      indicators
      img-width="1024"
      img-height="480"
    >
      <b-carousel-slide
        caption="First slide"
        v-model:img-src="imgsrcs[2]"
      ></b-carousel-slide>
      <b-carousel-slide
        caption="Second Slide"
        v-model:img-src="imgsrcs[2]"
      ></b-carousel-slide>
      <b-carousel-slide
        caption="Third Slide"
        v-model:img-src="imgsrcs[2]"
      ></b-carousel-slide>
    </b-carousel>

    <hr/>
    <!-- 加载本地的图片 -->
    <b-img :src="require('../assets/desert.jpg')"></b-img>
  </div>
</template>

<script>
  export default {
    name: "CarouselComponent",
    data() {
      return {
        imgsrcs: [
          {src: require('../assets/desert.jpg')},
          {src: require('../assets/mountain.jpg')},
          {src: require('../assets/ocean.jpg')}
        ]
      }
    }
  }
</script>

<style scoped>

</style>
