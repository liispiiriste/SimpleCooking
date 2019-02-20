<template>
    <div class="AddRecipe">
        <h2 style="float:left">Uus retsept:</h2>
        <form>
            Nimi: <input type="text" name="RecipeName"><br>
            Kirjeldus: <input type="text" name="Description"><br>
            Materialid: <input type="text" name="Materials"><br>
            Portsjon: <input type="number" name="Portion"><br>
            Hind: <input type="number" name="Price"><br>
            <input type="submit" value="Salvesta">
        </form>
    </div>
</template>
<script>
    export default{
        name:'AddRecipe',
        data (){
            return{
                title:'AddRecipe'
            }
        }
    }
</script>

<style scoped>

</style>