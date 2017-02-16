<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<title>three.js css3d - periodic table</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		<style>
			html, body {
				height: 100%;
			}
			body {
				background-color: #000000;
				background:url(threejs/login-bg2.jpg) top left;
				margin: 0;
				font-family: Helvetica, sans-serif;;
				overflow: hidden;
			}
			a {
				color: #ffffff;
			}
			#info {
				position: absolute;
				width: 100%;
				color: #ffffff;
				padding: 5px;
				font-family: Monospace;
				font-size: 13px;
				font-weight: bold;
				text-align: center;
				z-index: 1;
			}
			#menu {
				position: absolute;
				bottom: 20px;
				width: 100%;
				text-align: center;
			}
			.element {
				width: 405px;
				height: 388px;
				background:url(threejs/test.png) top left;
				/*box-shadow: 0px 0px 12px rgba(0,255,255,0.5);
				border: 1px solid rgba(127,255,255,0.25);*/
				/*box-shadow: 0px 0px 12px rgba(200, 54, 54, 0.5);*/
				/*border: 1px solid rgba(200, 54, 54, 0.5);*/
				text-align: center;
				cursor: default;
				
			}
			.element:hover {
				/*box-shadow: 0px 0px 12px rgba(0,255,255,0.75);*/
				/*box-shadow: 0px 0px 12px rgba(200, 54, 54, 0.5);*/
				/*border: 1px solid rgba(127,255,255,0.75);*/
				/*border: 1px solid rgba(200, 54, 54, 0.5);*/
			}
				.element .number {
					position: absolute;
					top: 20px;
					right: 20px;
					font-size: 26px;
					/*color: rgba(127,255,255,0.75);*/
					color: rgba(200, 54, 54, 0.5);
				}
				.element .symbol {
					position: absolute;
					top: 135px;
					left: 0px;
					right: 0px;
					font-size: 75px;
					font-weight: bold;
					/*color: rgba(255,255,255,0.75);*/
					color: rgba(255,255,255, 0.75);
					/*text-shadow: 0 0 10px rgba(0,255,255,0.95);*/
				}
				.element .details {
					position: absolute;
					bottom: 15px;
					left: 0px;
					right: 0px;
					font-size: 12px;
					/*color: rgba(127,255,255,0.75);*/
					color: rgba(200, 54, 54, 0.5);
				}
			button {
				color: rgba(127,255,255,0.75);
				background: transparent;
				outline: 1px solid rgba(127,255,255,0.75);
				border: 0px;
				padding: 5px 10px;
				cursor: pointer;
			}
			button:hover {
				background-color: rgba(0,255,255,0.5);
			}
			button:active {
				color: #000000;
				background-color: rgba(0,255,255,0.75);
			}
		</style>
	</head>
	<body>
		<script src="threejs/three.js"></script>
		<script src="threejs/tween.min.js"></script>
		<script src="threejs/TrackballControls.js"></script>
		<script src="threejs/CSS3DRenderer.js"></script>

		<div id="container"></div>
		<!--<div id="info"><a href="http://threejs.org" target="_blank">three.js css3d</a> - periodic table. <a href="https://plus.google.com/113862800338869870683/posts/QcFk5HrWran" target="_blank">info</a>.</div>-->
		<div id="menu">
			<button hidden="hidden" id="table">TABLE</button>
			<button hidden="hidden" id="sphere">SPHERE</button>
			<button hidden="hidden" id="helix">HELIX</button>
			<button hidden="hidden" id="grid">GRID</button>
		</div>

		<script>
			/*var table = [
				"H", "Hydrogen", "1.00794", 1, 1,
				"He", "Helium", "4.002602", 18, 1,
				"Li", "Lithium", "6.941", 1, 2,
				"Be", "Beryllium", "9.012182", 2, 2,
				"B", "Boron", "10.811", 13, 2,
				"C", "Carbon", "12.0107", 14, 2,
				"N", "Nitrogen", "14.0067", 15, 2,
				"O", "Oxygen", "15.9994", 16, 2,
				"F", "Fluorine", "18.9984032", 17, 2,
				"Ne", "Neon", "20.1797", 18, 2,
				"Na", "Sodium", "22.98976...", 1, 3,
				"Mg", "Magnesium", "24.305", 2, 3,
				"Al", "Aluminium", "26.9815386", 13, 3,
				"Si", "Silicon", "28.0855", 14, 3,
				"P", "Phosphorus", "30.973762", 15, 3,
				"S", "Sulfur", "32.065", 16, 3,
				"Cl", "Chlorine", "35.453", 17, 3,
				"Ar", "Argon", "39.948", 18, 3,
				"K", "Potassium", "39.948", 1, 4,
				"Ca", "Calcium", "40.078", 2, 4,
				"Sc", "Scandium", "44.955912", 3, 4,
				"Ti", "Titanium", "47.867", 4, 4,
				"V", "Vanadium", "50.9415", 5, 4,
				"As", "Arsenic", "74.9216", 15, 4,
				"Se", "Selenium", "78.96", 16, 4,
				"Br", "Bromine", "79.904", 17, 4,
				"Kr", "Krypton", "83.798", 18, 4,
				"Uuo", "Ununoctium", "(294)", 18, 7
			];*/
			
			/*var table = [
				"N", "Nitrogen", "14.0067", 15, 2,
				"Er", "Erbium", "167.259", 15, 9,
				"Db", "Dubnium", "(268)", 5, 7,
				"Es", "Einstenium", "(252)", 14, 10,
				"Bi", "Bismuth", "208.9804", 15, 6,
				"Rh", "Rhodium", "102.9055", 9, 5,
				"Mg", "Magnesium", "24.305", 2, 3,
				"Na", "Sodium", "22.98976...", 1, 3,
				"Ce", "Cerium", "140.116", 5, 9,
				"Mt", "Meitnerium", "(276)", 9, 7,
				"Rn", "Radon", "(222)", 18, 6,
				"Db", "Dubnium", "(268)", 5, 7,
				"H", "Hydrogen", "1.00794", 1, 1,
				"Be", "Beryllium", "9.012182", 2, 2,
				"F", "Fluorine", "18.9984032", 17, 2,
				"Sr", "Strontium", "87.62", 2, 5,
				"Ba", "Barium", "132.9054", 2, 6,
				"Np", "Neptunium", "(237)", 8, 10,
				"Cu", "Copper", "63.546", 11, 4,
				"Lv", "Livermorium", "(293)", 16, 7,
				"Dy", "Dysprosium", "162.5", 13, 9,
				"Md", "Mendelevium", "(258)", 16, 10,
				"W", "Tungsten", "183.84", 6, 6,
				"Sg", "Seaborgium", "(271)", 6, 7,
				"Kr", "Krypton", "83.798", 18, 4,
				"Uuo", "Ununoctium", "(294)", 18, 7
			];*/
			
						var table = [
				"版本管理", "Livermorium", "(293)", 16, 7,//Lv
				"设备监控", "Neptunium", "(237)", 8, 10,//Np
				"公司官网", "Barium", "132.9054", 2, 6,//Ba
				"用户中心", "Strontium", "87.62", 2, 5,//Sr
				"流量管理", "Fluorine", "18.9984032", 17, 2,//F
				"", "Hydrogen", "1.00794", 1, 1,//H
				"知识库", "Beryllium", "9.012182", 2, 2,//Be
				"游戏后台", "Copper", "63.546", 11, 4,//Cu
				"", "Dysprosium", "162.5", 13, 9,//Dy
				"", "Mendelevium", "(258)", 16, 10,//Md
				"", "Ununoctium", "(294)", 18, 7,//Uuo
				"", "Tungsten", "183.84", 6, 6,//W
				
				
				"", "Seaborgium", "(271)", 6, 7,//Sg
				"", "Krypton", "83.798", 18, 4,//Kr
				"", "Nitrogen", "14.0067", 15, 2,//N
				"", "Erbium", "167.259", 15, 9,//Er
				"", "Dubnium", "(268)", 5, 7,//Db
				"", "Einstenium", "(252)", 14, 10,//Es
				"", "Bismuth", "208.9804", 15, 6,//Bi
				"", "Rhodium", "102.9055", 9, 5,//Rh
				"行乐后台", "Magnesium", "24.305", 2, 3,//Mg
				"", "Sodium", "22.98976...", 1, 3,//Na
				"", "Cerium", "140.116", 5, 9,//Ce
				"Wifi 1.0", "Meitnerium", "(276)", 9, 7,//Mt
				"运维后台", "Radon", "(222)", 18, 6,//Rn
				"积分中心", "Dubnium", "(268)", 5, 7 //Db
			];
			
			
			
			var camera, scene, renderer;
			var controls;
			var objects = [];
			var targets = { table: [], sphere: [], helix: [], grid: [] };
			init();
			animate();
			function init() {
				camera = new THREE.PerspectiveCamera( 40, window.innerWidth / window.innerHeight, 1, 10000 );
				camera.position.z = 3000;
				scene = new THREE.Scene();
				// table
				for ( var i = 0; i < table.length; i += 5 ) {
					var element = document.createElement( 'div' );
					element.className = 'element';
					//element.style.backgroundColor = 'rgba(0,127,127,' + ( Math.random() * 0.5 + 0.25 ) + ')';
					var number = document.createElement( 'div' );
					number.className = 'number';
					number.textContent = (i/5) + 1;
					//element.appendChild( number );
					var symbol = document.createElement( 'div' );
					symbol.className = 'symbol';
					symbol.textContent = table[ i ];
					
					element.appendChild( symbol );
					element.onclick = jump;
					var details = document.createElement( 'div' );
					details.className = 'details';
					details.innerHTML = table[ i + 1 ] + '<br>' + table[ i + 2 ];
					element.appendChild( details );
					var object = new THREE.CSS3DObject( element );
					object.position.x = Math.random() * 4000 - 2000;
					object.position.y = Math.random() * 4000 - 2000;
					object.position.z = Math.random() * 4000 - 2000;
					scene.add( object );
					objects.push( object );
					//
					var object = new THREE.Object3D();
					object.position.x = ( table[ i + 3 ] * 140 ) - 1330;
					object.position.y = - ( table[ i + 4 ] * 180 ) + 990;
					targets.table.push( object );
				}
				// sphere
				var vector = new THREE.Vector3();
				for ( var i = 0, l = objects.length; i < l; i ++ ) {
					var phi = Math.acos( -1 + ( 2 * i ) / l );
					var theta = Math.sqrt( l * Math.PI ) * phi;
					var object = new THREE.Object3D();
					object.position.x = 800 * Math.cos( theta ) * Math.sin( phi );
					object.position.y = 800 * Math.sin( theta ) * Math.sin( phi );
					object.position.z = 800 * Math.cos( phi );
					vector.copy( object.position ).multiplyScalar( 2 );
					object.lookAt( vector );
					targets.sphere.push( object );
				}
				// helix
				var vector = new THREE.Vector3();
				for ( var i = 0, l = objects.length; i < l; i ++ ) {
					var phi = i * 0.175 + Math.PI;
					var object = new THREE.Object3D();
					object.position.x = 900 * Math.sin( phi );
					object.position.y = - ( i * 8 ) + 450;
					object.position.z = 900 * Math.cos( phi );
					vector.x = object.position.x * 2;
					vector.y = object.position.y;
					vector.z = object.position.z * 2;
					object.lookAt( vector );
					targets.helix.push( object );
				}
				// grid
				for ( var i = 0; i < objects.length; i ++ ) {
					var object = new THREE.Object3D();
					object.position.x = ( ( i % 5 ) * 400 ) - 800;
					object.position.y = ( - ( Math.floor( i / 5 ) % 5 ) * 400 ) + 800;
					object.position.z = ( Math.floor( i / 25 ) ) * 1000 - 2000;
					targets.grid.push( object );
				}
				//
				renderer = new THREE.CSS3DRenderer();
				renderer.setSize( window.innerWidth, window.innerHeight );
				renderer.domElement.style.position = 'absolute';
				document.getElementById( 'container' ).appendChild( renderer.domElement );
				//
				controls = new THREE.TrackballControls( camera, renderer.domElement );
				controls.rotateSpeed = 0.5;
				controls.minDistance = 500;
				controls.maxDistance = 6000;
				controls.addEventListener( 'change', render );
				var button = document.getElementById( 'table' );
				button.addEventListener( 'click', function ( event ) {
					transform( targets.table, 2000 );
				}, false );
				var button = document.getElementById( 'sphere' );
				button.addEventListener( 'click', function ( event ) {
					transform( targets.sphere, 2000 );
				}, false );
				var button = document.getElementById( 'helix' );
				button.addEventListener( 'click', function ( event ) {
					transform( targets.helix, 2000 );
				}, false );
				var button = document.getElementById( 'grid' );
				button.addEventListener( 'click', function ( event ) {
					transform( targets.grid, 2000 );
				}, false );
				//transform( targets.table, 2000 );
				transform( targets.sphere, 2000 );
				window.addEventListener( 'resize', onWindowResize, false );
			}
			function transform( targets, duration ) {
				TWEEN.removeAll();
				for ( var i = 0; i < objects.length; i ++ ) {
					var object = objects[ i ];
					var target = targets[ i ];
					new TWEEN.Tween( object.position )
						.to( { x: target.position.x, y: target.position.y, z: target.position.z }, Math.random() * duration + duration )
						.easing( TWEEN.Easing.Exponential.InOut )
						.start();
					new TWEEN.Tween( object.rotation )
						.to( { x: target.rotation.x, y: target.rotation.y, z: target.rotation.z }, Math.random() * duration + duration )
						.easing( TWEEN.Easing.Exponential.InOut )
						.start();
				}
				new TWEEN.Tween( this )
					.to( {}, duration * 2 )
					.onUpdate( render )
					.start();
			}
			function onWindowResize() {
				camera.aspect = window.innerWidth / window.innerHeight;
				camera.updateProjectionMatrix();
				renderer.setSize( window.innerWidth, window.innerHeight );
				render();
			}
			function animate() {
				requestAnimationFrame( animate );
				TWEEN.update();
				controls.update();
			}
			function render() {
				renderer.render( scene, camera );
			}
			function jump(element){
				//window.location.href='http://baidu.com';
				 //{ alert(element.srcElement.innerHTML); }
				 if(isContains( element.srcElement.innerHTML,'Wifi 1.0'))
				 	window.location.href='http://120.26.218.141/index';
				 if(isContains( element.srcElement.innerHTML,'知识库'))
				 	window.location.href='http://101.201.29.119/qmwiki';
				 if(isContains( element.srcElement.innerHTML,'行乐后台'))
					 	window.location.href='http://manage.1000mob.com/';
				 if(isContains( element.srcElement.innerHTML,'游戏后台'))
					 	window.location.href='http://open.1000mob.com/';
				 if(isContains( element.srcElement.innerHTML,'公司官网'))
					 	window.location.href='http://1000mob.com';
				 if(isContains( element.srcElement.innerHTML,'版本管理'))
					 	window.location.href='version/index';
				 if(isContains( element.srcElement.innerHTML,'设备监控'))
					 	window.location.href='monitor/index1';
			}
			function isContains(str, substr) {
    			return str.indexOf(substr) >= 0;
			}
		</script>
	</body>
</html>
