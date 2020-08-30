<template>
  <div class="container">
    <div>
      <b-table striped hover :items="items"></b-table>
    </div>
    <hr/>

    <div class="row">
      <div class="col col-12">
        <div>
          <b-table striped bordered hover :items="book"></b-table>
        </div>
      </div>
    </div>
    <hr/>

    <!--- b-table之中可以使用class来设置样式，但是推荐直接使用属性，这样更加方便 --->
    <div row>
      <div class="col col-12">
        <div>
          <b-table class="table-bordered table-striped table-info" hover :items="book"></b-table>
        </div>
      </div>
    </div>

    <!-- 按钮， 按钮组 -->
    <div row>
      <div class="col col-12">
        <div>
          <b-button variant="info">hello</b-button>
          <b-button variant="danger">hello</b-button>
          <b-button variant="success">hello</b-button>
        </div>
      </div>
      <!-- 按钮组 -->
      <div class="col col-12">
        <div>
          <b-button-group>
            <b-button variant="info">hello</b-button>
            <b-button variant="danger">hello</b-button>
            <b-button variant="success">hello</b-button>
          </b-button-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "TableComponent",data() {
        return {
          items: [
            { age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
            { age: 21, first_name: 'Larsen', last_name: 'Shaw' },
            { age: 89, first_name: 'Geneva', last_name: 'Wilson' },
            { age: 38, first_name: 'Jami', last_name: 'Carney' }
          ],
          book:[
            {书名: '朝花夕拾', 作者: '鲁迅', 价格: 22.6, 出版社: '文艺出版社', 年代: '30年代' },
            {书名: '朝花夕拾', 作者: '鲁迅', 价格: 22.6, 出版社: '文艺出版社', 年代: '30年代' },
            {书名: '朝花夕拾', 作者: '鲁迅', 价格: 22.6, 出版社: '文艺出版社', 年代: '30年代' },
            {书名: '朝花夕拾', 作者: '鲁迅', 价格: 22.6, 出版社: '文艺出版社', 年代: '30年代' },
            {书名: '朝花夕拾', 作者: '鲁迅', 价格: 22.6, 出版社: '文艺出版社', 年代: '30年代' },
            {书名: '朝花夕拾', 作者: '鲁迅', 价格: 22.6, 出版社: '文艺出版社', 年代: '30年代' }
          ]

        }
      }
    }
</script>

<style scoped>

</style>
