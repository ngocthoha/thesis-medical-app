<template>
  <div class="content" style="background-color: white">
    <div class="contentpage">
      <br />
      <p class="text-center display-1 font-weight-bold blue--text first-title">
        SẢN PHỤ KHOA
      </p>

      <br />

      <p class="text-left title font-weight-bold red--text mb-6 second-title">
        I. Giới thiệu chung
      </p>

      <p class="text-justify body-1">
        Chuyên khoá Sản phụ khoa của Phòng khám là đơn vị thăm khám, tầm soát và
        điều trị các bệnh lý phụ khoa toàn diện và chuyên sâu, giúp giải tỏa nỗi
        lo lắng cho phụ nữ về các bệnh phụ khoa, ngăn chặn những hậu quả ảnh
        hưởng đến sức khỏe sinh sản và sức khỏe giới tính cho phụ nữ.
      </p>

      <p class="text-justify body-1">
        Đối với Sản khoa phòng khám đầu tư nhiều trang thiết bị hiện đại cùng
        đội ngũ bác sĩ tận tâm, dày dạn kinh nghiệm giúp kiểm tra và theo dõi
        tình trạng sức khỏe của sản phụ và thai nhi từ những ngày đầu cho tới
        cuối thai kỳ; hỗ trợ và đem lại sự an tâm cho mẹ và bé “vượt cạn” an
        toàn.
      </p>

      <p class="text-left title font-weight-bold red--text mb-6 second-title">
        II. Các dịch vụ của chuyên khoa Sản phụ khoa
      </p>
      <p class="text-left body-1 font-weight-bold mb-3">1. Sản khoa</p>
      <p class="text-justify body-1">
        Các dịch vụ được thực hiện tại Sản khoa của Phòng khám bao gồm:
      </p>

      <p class="text-justify body-1">
        - Chẩn đoán và điều trị các trường hợp thai bệnh lý ở 3 tháng đầu thai
        kỳ như: dọa sảy thai, nhiễm độc thai nghén, thai lưu, thai ngoài tử
        cung, thai trứng…
      </p>

      <p class="text-justify body-1">
        - Tư vấn tiền sản và thực hiện các kỹ thuật khảo sát để phát hiện các
        bất thường trong giai đoạn sớm của thai kỳ, đặc biệt là các bệnh lý di
        truyền.
      </p>

      <p class="text-justify body-1">
        - Theo dõi sức khỏe mẹ, sức khỏe và sự phát triển của bé trong suốt thai
        kỳ.
      </p>

      <p class="text-left body-1 font-weight-bold mb-3">2. Phụ khoa</p>
      <p class="text-justify body-1">
        Các dịch vụ khám Phụ khoa được thực hiện tại Phòng khám bao gồm:
      </p>

      <p class="text-justify body-1">
        - Viêm nhiễm phụ khoa thường gặp (viêm âm hộ - âm đạo, viêm tử cung phần
        phụ…).
      </p>

      <p class="text-justify body-1">
        - Chẩn đoán và điều trị các khối u phụ khoa lành tính (u xơ tử cung, u
        nang buồng trứng, lạc nội mạc tử cung…).
      </p>

      <p class="text-justify body-1">
        - Tầm soát, chẩn đoán và điều trị các loại ung thư phụ khoa (ung thư cổ
        tử cung, ung thư nội mạc tử cung, ung thư buồng trứng…).
      </p>

      <br />

      <p class="text-center display-1 font-weight-bold blue--text">
        Các chuyên khoa khác
      </p>
      <CarouselSpecialist></CarouselSpecialist>
      <br />
    </div>
  </div>
</template>

<script>
import CarouselSpecialist from "../Component/carousel-specialist.vue";
export default {
  components: {
    CarouselSpecialist
  }
};
</script>

<style scoped>
.contentpage {
  margin: 0 100px;
  background: white;
}

.first-title {
  font-size: 30px !important;
}

.second-title {
  font-size: 20px !important;
}
</style>
