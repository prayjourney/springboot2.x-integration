<template>
  <div class="container">
    <div class="row">
      <div class="col col-12">
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
          <b-form-group id="input-group-5" label="邮箱:" label-for="input-1" description="填写您的邮箱.">
            <b-form-input id="input-1" v-model="form.email" type="email" required placeholder="输入邮箱"></b-form-input>
          </b-form-group>

          <b-form-group id="input-group-6" label="姓名:" label-for="input-2">
            <b-form-input id="input-2" v-model="form.name" required placeholder="输入姓名"></b-form-input>
          </b-form-group>

          <b-form-group id="input-group-7" label="Food:" label-for="input-3">
            <b-form-select id="input-3" v-model="form.food" :options="foods" required></b-form-select>
          </b-form-group>

          <b-form-group id="input-group-8">
            <b-form-checkbox-group v-model="form.checked" id="checkboxes-4">
              <b-form-checkbox value="me">Check me out</b-form-checkbox>
              <b-form-checkbox value="that">Check that out</b-form-checkbox>
            </b-form-checkbox-group>
          </b-form-group>

          <b-button type="submit" variant="primary">Submit</b-button>
          <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
      </div>

    </div>
    <hr/>

    <!-- 放在了同一行， 但是对的不齐， 栅格系统排布 -->
    <div class="row">
      <div class="col col-12">
        <b-form @submit="onSubmit2" @reset="onReset2" v-if="show">
          <b-form-group id="input-group-1">
            <label label-for="input_5" class="col col-2">邮箱：</label>
            <b-form-input class="col col-10 float-right" id="input_5" v-model="form.email" type="email" required placeholder="输入邮箱"></b-form-input>
          </b-form-group>

          <b-form-group id="input-group-2">
            <label label-for="input-6" class="col col-2 float-left">姓名：</label>
            <b-form-input id="input-6" class="col col-10" v-model="form.name" required placeholder="输入姓名"></b-form-input>
          </b-form-group>

          <b-form-group id="input-group-3" class="form-group">
            <label label-for="input-7" class="col col-2 float-left">食物：</label>
            <b-form-select id="input-7" class="col col-10" v-model="form.food" :options="foods" required></b-form-select>
          </b-form-group>

          <b-form-group id="input-group-4">
            <b-form-checkbox-group v-model="form.checked" id="checkboxes-4">
              <b-form-checkbox value="me">Check me out</b-form-checkbox>
              <b-form-checkbox value="that">Check that out</b-form-checkbox>
            </b-form-checkbox-group>
          </b-form-group>

          <b-button type="submit" variant="primary">Submit</b-button>
          <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
      </div>
    </div>

    <hr/>

    <!-- 行内的form -->
    <div>
      <b-form inline>
        <label class="sr-only" for="inline-form-input-name">Name</label>
        <b-input id="inline-form-input-name" class="mb-2 mr-sm-2 mb-sm-0" placeholder="Jane Doe"></b-input>
        <label class="sr-only" for="inline-form-input-username">Username</label>
        <b-input-group prepend="@" class="mb-2 mr-sm-2 mb-sm-0">
          <b-input id="inline-form-input-username" placeholder="Username"></b-input>
        </b-input-group>
        <b-form-checkbox class="mb-2 mr-sm-2 mb-sm-0">Remember me</b-form-checkbox>
        <b-button variant="primary">Save</b-button>
      </b-form>
    </div>
  </div>
</template>

<script>
  export default {
    name: "FormComponent",
    data() {
      return {
        form: {
          email: '',
          name: '',
          food: null,
          checked: []
        },
        foods: [{text: 'Select One', value: null}, 'Carrots', 'Beans', 'Tomatoes', 'Corn'],
        show: true
      }
    },
    methods: {
      onSubmit(evt) {
        evt.preventDefault()
        alert(JSON.stringify(this.form))
      },
      onReset(evt) {
        evt.preventDefault()
        // Reset our form values
        this.form.email = ''
        this.form.name = ''
        this.form.food = null
        this.form.checked = []
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      },
      onSubmit2(evt) {
        evt.preventDefault()
        alert(JSON.stringify(this.form))
      },
      onReset2(evt) {
        evt.preventDefault()
        // Reset our form values
        this.form.email = ''
        this.form.name = ''
        this.form.food = null
        this.form.checked = []
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    }
  }
</script>

<style scoped>

</style>
