<template>
  <v-app>
    <default-bar />
    <v-row justify="center"
      ><v-col cols="8"><default-view /> </v-col
    ></v-row>
  </v-app>
</template>

<script>
export default {
  name: "UserLayout",

  components: {
    DefaultBar: () =>
      import(
        /* webpackChunkName: "default-app-bar" */
        "./AppBar"
      ),
    // DefaultFooter: () =>
    //   import(
    //     /* webpackChunkName: "default-footer" */
    //     "./Footer"
    //   ),
    // DefaultSettings: () =>
    //   import(
    //     /* webpackChunkName: "default-settings" */
    //     "./Settings"
    //   ),
    DefaultView: () =>
      import(
        /* webpackChunkName: "default-view" */
        "./View"
      )
  }
};
</script>
