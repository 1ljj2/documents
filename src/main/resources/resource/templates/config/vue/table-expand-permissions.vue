<style scoped>
    .expand-row {
        margin-bottom: 16px;
    }
</style>
<template>
    <div>
        hhhh
        <Row class="expand-row">
            <Col span="12">
                <span class="expand-key">权限类型: </span>
                <span class="expand-value">{{ row.typeDescription }}</span>
            </Col>
            <Col span="12">
                <span class="expand-key">权限模块: </span>
                <span class="expand-value">{{ row.moduleDescription }}</span>
            </Col>
        </Row>
    </div>
</template>
<script>
    export default {
        props: {
            row: Object
        }
    };
</script>