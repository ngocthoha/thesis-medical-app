<template>
    <div class="content">
        <div class="md-layout">
            <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-60">
                <div class="login-section col">
                    <strong>Đăng nhập</strong>
                    <label for="">Tên đăng nhập</label>
                    
                    <label for="">Mật khẩu</label>
                </div>
                <div class="login-img col">
                    <img src="@/assets/img/login-image.png" alt="doctor" class="login-img">
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
    .md-layout-item{
        background: white;
        margin:auto;
    }
    .content .login-img{
        width: 50%px;
        height: 417px;
        float: right;
    }
    .col{
        width: 50%;
        float: left;
    }
</style>