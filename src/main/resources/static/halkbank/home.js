import{a as n}from"./chunks/C4C4J4DN.js";import{a,b as s,c as h,d as r}from"./chunks/3MV3BBWP.js";import"./chunks/XUOQRPBR.js";import{c}from"./chunks/2FOZCZO5.js";var o=c(h()),m={name:"exchangeRatesMini",data(e={}){return{config:e,data:null,ratesList:[],init(){this.getData()},getData(){(0,o.default)({method:"get",url:this.config.url}).then(t=>{if(t.data.result){let p=(typeof t=="string"?JSON.parse(t.data):t.data).data.currencyHistory[0];this.data=p.branches["050201"],this.filterRates()}else alert("\u041E\u0448\u0438\u0431\u043A\u0430 \u0432 \u043F\u043E\u043B\u0443\u0447\u0435\u043D\u0438\u0435 \u0434\u0430\u043D\u043D\u044B\u0445")})},filterRates(){Object.keys(this.data).forEach(t=>{let i=t.match(/^[^/]+/g)[0];this.config.filters.includes(i)&&this.ratesList.push({name:i,rates:this.data[t]})})}}}};var l=[n,m,r];l.forEach(e=>{a.data(e.name,e.data)});window.Alpine=a;a.plugin(s);a.start();