<template>
  <div class="content">
    <div class="md-layout select-opd-page">
      <div class="search-opd-section">
        <label class="search-ope-label">Tìm nhanh chuyên khoa</label>
        <input type="text" v-model="search" placeholder="Nhập chuyên khoa.." />
      </div>

      <div class="list-opd-section">
        <!-- <md-table v-model="OPDList" md-card @md-selected="onSelect" class="md-scrollbar">

               <md-table-row slot="md-table-row" slot-scope="{ item }"  md-selectable="single">
                 <p class="opd-name">{{item.name}}</p>
               </md-table-row>
             </md-table> -->
        <!-- <md-table v-for="OPD in filteredList" v-bind:key="OPD.name" md-card @md-selected="onSelect" class="md-scrollbar">

               <md-table-row :key="OPD.name" md-selectable="single">
                 <p class="opd-name">{{OPD.name}}</p>
               </md-table-row>
             </md-table> -->
        <md-table
          v-model="filteredList"
          md-card
          @md-selected="onSelect"
          class="md-scrollbar"
        >
          <md-table-row
            slot="md-table-row"
            slot-scope="{ item }"
            md-selectable="single"
          >
            <p class="opd-name">{{ item.name }}</p>
          </md-table-row>
        </md-table>
      </div>
    </div>
    <p>Bạn đã chọn khoa: {{ selected }}</p>
  </div>
</template>

<script>
class OPD {
  constructor(name) {
    this.name = name;
  }
}
export default {
  created() {
    this.getODPList();
  },
  data() {
    return {
      hasSelect: false,
      search: "",
      selected: "",
      OPDList: [
        // new OPD("Da Liễu"),
        // new OPD("Tim Mạch"),
        // new OPD("Nội Thận"),
        // new OPD("Cấp cứu"),
        // new OPD("Hô Hấp"),
        // new OPD("Nội Thận"),
        // new OPD("Da Liễu"),
        // new OPD("Tim Mạch"),
        // new OPD("Nội Thận"),
        // new OPD("Da Liễu"),
        // new OPD("Tim Mạch"),
        // new OPD("Nội Thận"),
      ],
    };
  },
  computed: {
    filteredList() {
      return this.OPDList.filter((OPD) => {
        return OPD.name.toLowerCase().includes(this.search.toLowerCase());
      });
    },
  },
  methods: {
    onSelect(item) {
      this.selected = item.name;
    },

    getSelected() {
      this.selected;
    },

    async getODPList() {
      const params = {
        token: this.$store.getters["auth/access_token"],
      };
      await this.$store.dispatch("appointment/speciality_list", params);
      let specialist = this.$store.getters["appointment/speciality_list"];
      console.log(specialist);
      specialist.forEach((element) => {
        this.OPDList.push(new OPD(element));
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.select-opd-page {
  flex-direction: column;
  align-items: center;
  width: 800px;
}
.list-opd-section {
  width: 100%;
}
.opd-name {
  margin-left: 10px;
  margin-top: 10px;
}

.row-opd {
  height: 60px;

  margin: 2px;
  border-style: solid;
  border-color: #4caf50;
  border-width: thin;
  opacity: 0.87;
  font-weight: 500;
}

.search-opd-section {
  width: 100%;
}
.search-ope-label {
  margin-left: 150px;
  margin-right: 50px;
  font-weight: 500;
}
.md-table.md-theme-default .md-table-row.md-selected-single {
  background-color: #4caf50;
  opacity: 0.87;
  color: white;
}

.md-table {
  max-width: 100%;
  max-height: 205px;
  overflow: auto;
}
</style>
