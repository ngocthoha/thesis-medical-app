<template>
  <v-main>
    <v-container fluid class="pa-0">
      <router-view :key="$route.path" v-show="isUserPage" />
      <div class="d-flex flex-row">
        <v-card height="400" width="256" class="mx-auto" v-show="!isUserPage">
          <v-navigation-drawer permanent>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title class="text-h6">
                  Application
                </v-list-item-title>
                <v-list-item-subtitle>
                  subtext
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-divider></v-divider>

            <v-list dense nav>
              <v-list-item v-for="item in items" :key="item.title" link>
                <v-list-item-icon>
                  <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                  <v-list-item-title>{{ item.title }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-navigation-drawer>
        </v-card>
        <router-view :key="$route.path" />
      </div>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: "DefaultView",
  data() {
    return {
      items: [
        { title: "Dashboard", icon: "mdi-view-dashboard" },
        { title: "Photos", icon: "mdi-image" },
        { title: "About", icon: "mdi-help-box" }
      ],
      right: null
    };
  },
  methods: {
    isUserPage() {
      if (this.$route.name != "Trang chủ") {
        return true;
      }
    }
  }
};
</script>
