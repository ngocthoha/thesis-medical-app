<template>
  <v-app>
    <default-bar />
    <default-view />
    <default-footer />
    <v-snackbar v-model="snackbar" centered>
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn color="pink" text v-bind="attrs" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-app>
</template>

<script>
export default {
  name: "HomeLayout",
  data: () => ({
    snackbar: true,
    text: `Hello, I'm a snackbar`
  }),
  components: {
    DefaultBar: () =>
      import(
        /* webpackChunkName: "default-app-bar" */
        "./AppBar"
      ),
    DefaultDrawer: () =>
      import(
        /* webpackChunkName: "default-drawer" */
        "./Drawer"
      ),
    DefaultFooter: () =>
      import(
        /* webpackChunkName: "default-footer" */
        "./Footer"
      ),
    DefaultSettings: () =>
      import(
        /* webpackChunkName: "default-settings" */
        "./Settings"
      ),
    DefaultView: () =>
      import(
        /* webpackChunkName: "default-view" */
        "./View"
      )
  }
};
</script>
