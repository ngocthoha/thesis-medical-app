<template>
    <div class="content">
        <div class="md-layout select-opd-page">
           <div class="search-opd-section">
               <label>Tìm nhanh chuyên khoa</label>
               <input type="text" v-model="search" placeholder="Nhập chuyên khoa.."/>
           </div>
           <div class="list-opd-section">
               <label>Tìm nhanh chuyên khoa</label>
               <input type="text" v-model="search" placeholder="Nhập chuyên khoa.."/>
           </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
    .select-opd-page{
      flex-direction: column;
      align-items: center;
    }
</style>